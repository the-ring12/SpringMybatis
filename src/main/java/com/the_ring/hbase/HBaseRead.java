package com.the_ring.hbase;

import com.the_ring.hbase.entity.PageCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author: the_ring
 * Time: 2021/12/1 16:46
 * log: HBase 操作类，从 HBase 中读取数据
 */
@Repository
public class HBaseRead {

    private Configuration conf;
    private HTable hTable;

    public HBaseRead() throws IOException {
        conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.94.61,192.168.94.62,192.168.94.63");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        hTable = new HTable(conf, "page");
    }

    /**
     * 获取某个页面的访问数
     *
     * @param page
     * @return
     */
    public int getCount(String page) {
        int count = 0;
        try {
            Get get = new Get(Bytes.toBytes(page));
            Result result = hTable.get(get);
            List<Cell> cells = result.listCells();
            for (Cell cell : cells) {
                String s = new String(cell.getQualifier());
                System.out.print(s + "   ");
                if (s.equals("count")) {
                    count = Integer.parseInt(Bytes.toHex(cell.getValue()), 16);
                    System.out.println(count);
                } else {
                    String value = new String(cell.getValue());
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 获取每个页面的访问数
     * @return
     * @throws IOException
     */
    public List<PageCount> getAllCount() throws IOException {
        List<PageCount> pageCounts = new ArrayList<>();
        ResultScanner scanner = hTable.getScanner(new Scan());
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            PageCount pageCount = new PageCount();
            Result next = iterator.next();
            List<Cell> cells = next.listCells();
            for (Cell cell : cells) {
                String s = new String(cell.getQualifier());
                System.out.print(s + "   ");
                if (s.equals("count")) {
                    int count = Integer.parseInt(Bytes.toHex(cell.getValue()), 16);
                    pageCount.setCount(count);
                    System.out.println(count);
                } else if (s.equals("page")) {
                    String[] split = new String(cell.getValueArray()).split("/");
                    System.out.println(split[split.length - 1]);
                    pageCount.setPage(split[split.length - 1]);
                } else {
                    String value = new String(cell.getValue());
                    System.out.println(value);
                }
            }
            pageCounts.add(pageCount);
        }

        return pageCounts;
    }
}
