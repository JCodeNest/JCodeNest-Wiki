package cn.jcodenest.wiki.common.utils;

import cn.jcodenest.wiki.common.constant.CommonConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 文件操作工具类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 文件扩展名（不包含点），如果没有扩展名则返回空字符串
     */
    public static String getFileExtension(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return CommonConstants.String.EMPTY;
        }
        
        int lastDotIndex = fileName.lastIndexOf(CommonConstants.String.DOT);
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return CommonConstants.String.EMPTY;
        }
        
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }

    /**
     * 获取不带扩展名的文件名
     *
     * @param fileName 文件名
     * @return 不带扩展名的文件名
     */
    public static String getFileNameWithoutExtension(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return CommonConstants.String.EMPTY;
        }
        
        int lastDotIndex = fileName.lastIndexOf(CommonConstants.String.DOT);
        if (lastDotIndex == -1) {
            return fileName;
        }
        
        return fileName.substring(0, lastDotIndex);
    }

    /**
     * 判断是否为图片文件
     *
     * @param fileName 文件名
     * @return true-图片文件，false-非图片文件
     */
    public static boolean isImageFile(String fileName) {
        String extension = getFileExtension(fileName);
        return Arrays.asList(CommonConstants.File.IMAGE_EXTENSIONS).contains(extension);
    }

    /**
     * 判断是否为视频文件
     *
     * @param fileName 文件名
     * @return true-视频文件，false-非视频文件
     */
    public static boolean isVideoFile(String fileName) {
        String extension = getFileExtension(fileName);
        return Arrays.asList(CommonConstants.File.VIDEO_EXTENSIONS).contains(extension);
    }

    /**
     * 判断是否为音频文件
     *
     * @param fileName 文件名
     * @return true-音频文件，false-非音频文件
     */
    public static boolean isAudioFile(String fileName) {
        String extension = getFileExtension(fileName);
        return Arrays.asList(CommonConstants.File.AUDIO_EXTENSIONS).contains(extension);
    }

    /**
     * 判断是否为文档文件
     *
     * @param fileName 文件名
     * @return true-文档文件，false-非文档文件
     */
    public static boolean isDocumentFile(String fileName) {
        String extension = getFileExtension(fileName);
        return Arrays.asList(CommonConstants.File.DOCUMENT_EXTENSIONS).contains(extension);
    }

    /**
     * 格式化文件大小
     *
     * @param size 文件大小（字节）
     * @return 格式化后的文件大小字符串
     */
    public static String formatFileSize(long size) {
        if (size < 0) {
            return "0 B";
        }
        
        String[] units = {"B", "KB", "MB", "GB", "TB", "PB"};
        int unitIndex = 0;
        double fileSize = size;
        
        while (fileSize >= 1024 && unitIndex < units.length - 1) {
            fileSize /= 1024;
            unitIndex++;
        }
        
        return String.format("%.2f %s", fileSize, units[unitIndex]);
    }

    /**
     * 计算文件MD5值
     *
     * @param file 文件
     * @return MD5值，计算失败返回null
     */
    public static String calculateMD5(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            return calculateMD5Internal(inputStream);
        } catch (IOException e) {
            log.error("计算文件MD5失败: {}", file.getAbsolutePath(), e);
            return null;
        }
    }

    /**
     * 计算输入流MD5值
     *
     * @param inputStream 输入流
     * @return MD5值，计算失败返回null
     */
    public static String calculateMD5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        return calculateMD5Internal(inputStream);
    }

    /**
     * 内部方法：计算输入流的MD5值
     *
     * @param inputStream 输入流
     * @return MD5值，计算失败返回null
     */
    private static String calculateMD5Internal(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                md5.update(buffer, 0, bytesRead);
            }

            byte[] digest = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("计算输入流MD5失败", e);
            return null;
        }
    }

    /**
     * 创建目录（如果不存在）
     *
     * @param dirPath 目录路径
     * @return true-创建成功或已存在，false-创建失败
     */
    public static boolean createDirectoryIfNotExists(String dirPath) {
        if (StringUtils.isBlank(dirPath)) {
            return false;
        }
        
        try {
            Path path = Paths.get(dirPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                log.debug("创建目录: {}", dirPath);
            }
            return true;
        } catch (IOException e) {
            log.error("创建目录失败: {}", dirPath, e);
            return false;
        }
    }

    /**
     * 复制文件
     *
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件路径
     * @return true-复制成功，false-复制失败
     */
    public static boolean copyFile(String sourcePath, String targetPath) {
        if (StringUtils.isBlank(sourcePath) || StringUtils.isBlank(targetPath)) {
            return false;
        }
        
        try {
            Path source = Paths.get(sourcePath);
            Path target = Paths.get(targetPath);
            
            // 确保目标目录存在
            createDirectoryIfNotExists(target.getParent().toString());
            
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            log.debug("复制文件: {} -> {}", sourcePath, targetPath);
            return true;
        } catch (IOException e) {
            log.error("复制文件失败: {} -> {}", sourcePath, targetPath, e);
            return false;
        }
    }

    /**
     * 移动文件
     *
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件路径
     * @return true-移动成功，false-移动失败
     */
    public static boolean moveFile(String sourcePath, String targetPath) {
        if (StringUtils.isBlank(sourcePath) || StringUtils.isBlank(targetPath)) {
            return false;
        }
        
        try {
            Path source = Paths.get(sourcePath);
            Path target = Paths.get(targetPath);
            
            // 确保目标目录存在
            createDirectoryIfNotExists(target.getParent().toString());
            
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            log.debug("移动文件: {} -> {}", sourcePath, targetPath);
            return true;
        } catch (IOException e) {
            log.error("移动文件失败: {} -> {}", sourcePath, targetPath, e);
            return false;
        }
    }

    /**
     * 删除文件或目录
     *
     * @param path 文件或目录路径
     * @return true-删除成功，false-删除失败
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isBlank(path)) {
            return false;
        }

        try {
            Path filePath = Paths.get(path);
            if (Files.exists(filePath)) {
                if (Files.isDirectory(filePath)) {
                    // 递归删除目录（先删除文件，再删除目录）
                    try (Stream<Path> pathStream = Files.walk(filePath)) {
                        pathStream.sorted(Comparator.reverseOrder())
                                .forEach(p -> {
                                    try {
                                        Files.delete(p);
                                    } catch (IOException e) {
                                        log.warn("删除文件失败: {}", p, e);
                                    }
                                });
                    }
                } else {
                    Files.delete(filePath);
                }

                log.debug("删除文件: {}", path);
                return true;
            }

            // 文件不存在，认为删除成功
            return true;
        } catch (IOException e) {
            log.error("删除文件失败: {}", path, e);
            return false;
        }
    }

    /**
     * 读取文件内容为字符串
     *
     * @param filePath 文件路径
     * @return 文件内容，读取失败返回null
     */
    public static String readFileToString(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("读取文件失败: {}", filePath, e);
            return null;
        }
    }

    /**
     * 将字符串写入文件
     *
     * @param content  文件内容
     * @param filePath 文件路径
     * @return true-写入成功，false-写入失败
     */
    public static boolean writeStringToFile(String content, String filePath) {
        if (content == null || StringUtils.isBlank(filePath)) {
            return false;
        }
        
        try {
            Path path = Paths.get(filePath);
            // 确保目录存在
            createDirectoryIfNotExists(path.getParent().toString());
            
            Files.writeString(path, content, StandardCharsets.UTF_8);
            log.debug("将字符串写入文件: {}", filePath);
            return true;
        } catch (IOException e) {
            log.error("将字符串写入文件失败: {}", filePath, e);
            return false;
        }
    }

    /**
     * 读取文件内容为字节数组
     *
     * @param filePath 文件路径
     * @return 文件内容字节数组，读取失败返回null
     */
    public static byte[] readFileToByteArray(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            log.error("读取文件失败: {}", filePath, e);
            return null;
        }
    }

    /**
     * 将字节数组写入文件
     *
     * @param data     字节数组
     * @param filePath 文件路径
     * @return true-写入成功，false-写入失败
     */
    public static boolean writeByteArrayToFile(byte[] data, String filePath) {
        if (data == null || StringUtils.isBlank(filePath)) {
            return false;
        }
        
        try {
            Path path = Paths.get(filePath);
            // 确保目录存在
            createDirectoryIfNotExists(path.getParent().toString());
            
            Files.write(path, data);
            log.debug("将字节数组写入文件: {}", filePath);
            return true;
        } catch (IOException e) {
            log.error("将字节数组写入文件失败: {}", filePath, e);
            return false;
        }
    }

    /**
     * 生成唯一文件名
     *
     * @param originalFileName 原始文件名
     * @return 唯一文件名
     */
    public static String generateUniqueFileName(String originalFileName) {
        if (StringUtils.isBlank(originalFileName)) {
            return StringUtils.uuid();
        }
        
        String extension = getFileExtension(originalFileName);
        String uuid = StringUtils.uuid();
        
        if (StringUtils.isNotBlank(extension)) {
            return uuid + CommonConstants.String.DOT + extension;
        }
        
        return uuid;
    }

    /**
     * 获取文件MIME类型
     *
     * @param fileName 文件名
     * @return MIME类型，获取失败返回"application/octet-stream"
     */
    public static String getMimeType(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "application/octet-stream";
        }
        
        try {
            Path path = Paths.get(fileName);
            String mimeType = Files.probeContentType(path);
            return mimeType != null ? mimeType : "application/octet-stream";
        } catch (IOException e) {
            log.warn("获取MIME类型失败: {}", fileName, e);
            return "application/octet-stream";
        }
    }
}
