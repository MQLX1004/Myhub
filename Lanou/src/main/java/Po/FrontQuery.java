package Po;/**
 * Created by pc on 2018/3/31.
 */

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/31
 */
public class FrontQuery {
    private int page;
    private int rows;
    private int pageSize;
    private int start;
    private String huishouid;

    public String getHuishouid() {
        return huishouid;
    }

    public void setHuishouid(String huishouid) {
        this.huishouid = huishouid;
    }

    public FrontQuery() {
    }

    public FrontQuery(int start, int pageSize) {
        this.pageSize = pageSize;
        this.start = start;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
