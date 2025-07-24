package cn.jcodenest.wiki.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页响应结果封装类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 数据列表
     */
    private List<T> records;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Long current;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 私有构造函数
     */
    private PageResult() {
    }

    /**
     * 构造函数
     *
     * @param records 数据列表
     * @param total   总记录数
     * @param current 当前页码
     * @param size    每页大小
     */
    private PageResult(List<T> records, Long total, Long current, Long size) {
        this.records = records != null ? records : Collections.emptyList();
        this.total = total != null ? total : 0L;
        this.current = current != null ? current : 1L;
        this.size = size != null ? size : 10L;
        
        // 计算总页数
        this.pages = this.size > 0 ? (this.total + this.size - 1) / this.size : 0L;
        
        // 计算是否有上一页和下一页
        this.hasPrevious = this.current > 1;
        this.hasNext = this.current < this.pages;
    }

    /**
     * 创建分页结果
     *
     * @param records 数据列表
     * @param total   总记录数
     * @param current 当前页码
     * @param size    每页大小
     * @param <T>     数据类型
     * @return PageResult对象
     */
    public static <T> PageResult<T> of(List<T> records, Long total, Long current, Long size) {
        return new PageResult<>(records, total, current, size);
    }

    /**
     * 创建分页结果（使用默认页码和大小）
     *
     * @param records 数据列表
     * @param total   总记录数
     * @param <T>     数据类型
     * @return PageResult对象
     */
    public static <T> PageResult<T> of(List<T> records, Long total) {
        return new PageResult<>(records, total, 1L, 10L);
    }

    /**
     * 创建空的分页结果
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param <T>     数据类型
     * @return PageResult对象
     */
    public static <T> PageResult<T> empty(Long current, Long size) {
        return new PageResult<>(Collections.emptyList(), 0L, current, size);
    }

    /**
     * 创建空的分页结果（使用默认页码和大小）
     *
     * @param <T> 数据类型
     * @return PageResult对象
     */
    public static <T> PageResult<T> empty() {
        return new PageResult<>(Collections.emptyList(), 0L, 1L, 10L);
    }

    /**
     * 判断是否为空
     *
     * @return true-空，false-非空
     */
    public boolean isEmpty() {
        return records == null || records.isEmpty();
    }

    /**
     * 判断是否非空
     *
     * @return true-非空，false-空
     */
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    /**
     * 获取记录数量
     *
     * @return 记录数量
     */
    public int getRecordCount() {
        return records != null ? records.size() : 0;
    }

    /**
     * 是否为第一页
     *
     * @return true-第一页，false-非第一页
     */
    public boolean isFirstPage() {
        return current != null && current <= 1;
    }

    /**
     * 是否为最后一页
     *
     * @return true-最后一页，false-非最后一页
     */
    public boolean isLastPage() {
        return current != null && pages != null && current.equals(pages);
    }

    /**
     * 获取下一页页码
     *
     * @return 下一页页码，如果没有下一页则返回当前页码
     */
    public Long getNextPage() {
        return hasNext ? current + 1 : current;
    }

    /**
     * 获取上一页页码
     *
     * @return 上一页页码，如果没有上一页则返回当前页码
     */
    public Long getPreviousPage() {
        return hasPrevious ? current - 1 : current;
    }
}
