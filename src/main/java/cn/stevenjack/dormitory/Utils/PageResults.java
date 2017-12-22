package cn.stevenjack.dormitory.Utils;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResults<T> implements Serializable {

    private static final long serialVersionUID = 8459184039167837494L;
    //上一页
    @Getter
    private int previousPage;

    // 当前页
    @Getter
    private int currentPage;

    // 下一页
    @Getter
    private int nextPage;

    // 每页数量
    @Getter
    private int pageSize;

    // 总条数
    @Getter
    @Setter
    private int totalCount;

    // 总页数
    @Getter
    @Setter
    private int pageCount;

    // 记录
    @Getter
    @Setter
    private List<T> results;

    public void initPageResults(final int pageSize,
                                final int totalCount,
                                final int currentPage) {
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        this.setPageCount(createPageCount());


        this.setCurrentPage(currentPage);
        this.setNextPage(this.getCurrentPage() + 1);
        this.setPreviousPage(this.getCurrentPage() - 1);
    }

    public void setPreviousPage(int previousPage) {
        if (previousPage <= 0 || pageCount == 0) {
            this.previousPage = 1;
        } else {
            this.previousPage = previousPage;
        }
    }

    public void setNextPage(int nextPage) {
        if (nextPage <= 0 || pageCount == 0) {
            this.nextPage = 1;
        } else {
            if (nextPage > pageCount && pageCount > 0) {
                this.nextPage = pageCount;
            } else {
                this.nextPage = nextPage;
            }
        }
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0 || pageCount == 0) {
            this.currentPage = 1;
        } else {
            if (currentPage > pageCount && pageCount > 0) {
                this.currentPage = pageCount;
            } else {
                this.currentPage = currentPage;
            }
        }
    }

    //默认每页十条
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public int createPageCount() {
        return totalCount % pageSize == 0 ? totalCount / pageSize
                : totalCount / pageSize + 1;
    }
}
