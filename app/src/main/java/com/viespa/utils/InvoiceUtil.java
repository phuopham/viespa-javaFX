package com.viespa.utils;

import com.viespa.models.Course;
import com.viespa.models.Customer;
import com.viespa.models.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class InvoiceUtil {
    static final String stamp = "<svg fill=\"red\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" \n" +
            "\t width=\"100px\" height=\"100px\" viewBox=\"0 0 959.199 959.199\"\n" +
            "\t xml:space=\"preserve\">\n" +
            "<g>\n" +
            "\t<path d=\"M788.143,259.2c-16.699-44.2-41.399-81.7-73.399-111.5c-32.7-30.5-73-52.8-120-66.5c0,0,0,0-0.101-0.1\n" +
            "\t\tc-52.3-52.3-121.9-81.1-195.9-81.1s-143.5,28.8-195.8,81.1c-52.3,52.3-81.1,121.9-81.1,195.9c0,18.9,1.9,37.5,5.6,55.7\n" +
            "\t\tc-14.3,41.7-20.8,85.9-19.4,132.101c2.4,78.5,27.9,160.899,75.6,244.899c0.1,0.102,0.2,0.301,0.3,0.4\n" +
            "\t\tc18.6,140.6,138.9,249.1,284.6,249.1c128.3,0,237-84.199,273.8-200.398c9.8-7.5,19.101-15.602,27.9-24.5\n" +
            "\t\tc52.3-52.301,81.1-121.9,81.1-195.9c0-50.9-13.6-99.701-39.1-142.201C812.443,370.3,809.843,316.5,788.143,259.2z M597.643,705.199\n" +
            "\t\tc-33.5,0-60.6-27.1-60.6-60.6s27.1-60.6,60.6-60.6s60.601,27.1,60.601,60.6S631.043,705.199,597.643,705.199z M630.843,435.2h145.9\n" +
            "\t\tc16.2,31.601,24.7,66.701,24.7,103.201c0,50-16.101,97.5-45.801,136.699c0-1,0-2.1,0-3.1\n" +
            "\t\tC755.643,573.699,706.143,486.9,630.843,435.2z M762.143,385.2h-20.3c-76.8,0-153.399-1.2-230.3-1.3\n" +
            "\t\tc-55.8,0-112.901,5-163.901,27.7c-83.399,37.1-133.399,117.099-155.1,203c-1.2-2.9-2.3-5.799-3.5-8.699\n" +
            "\t\tc-19.2-49-29.7-96.801-31.1-142.701c-0.5-15.7,0.1-31.1,1.7-46.2c1.5-14,3.9-27.8,7.2-41.3c3.3-13.6,7.5-26.8,12.6-39.8\n" +
            "\t\tc5.8-14.6,12.7-28.9,20.7-42.7c30.8-52.9,77.3-97.7,134.6-129.4c53.399-29.6,113.399-45.9,168.8-45.9c17.6,0,34.6,1.6,50.6,4.9\n" +
            "\t\tc4.7,1,9.4,2,13.9,3.1c14.7,3.6,28.7,8.2,41.8,13.6c14.4,6,27.9,13,40.3,21.2c41.301,26.9,71.7,65.5,90.801,115.6\n" +
            "\t\tc7,18.4,11.699,36.5,14.899,53.1c2.9,14.9,4.5,28.6,5.3,40.2C761.743,375.2,762.043,380.5,762.143,385.2z M398.842,50\n" +
            "\t\tc31.2,0,61.4,6.3,89.3,18.2c-59.3,2.7-121.7,20.9-177.5,51.8c-55,30.5-101.399,71.7-135.8,120.4c7.5-46.7,29.4-89.7,63.5-123.8\n" +
            "\t\tC281.142,73.6,338.142,50,398.842,50z M342.443,705.199c-33.5,0-60.601-27.1-60.601-60.6c0-18.799,8.5-35.5,21.9-46.6\n" +
            "\t\tc6.7-5.5,14.5-9.6,23.1-11.9c5-1.299,10.2-2,15.5-2c3.3,0,6.5,0.301,9.7,0.801c28.9,4.6,50.9,29.6,50.9,59.799\n" +
            "\t\tc0,11.602-3.301,22.5-8.9,31.701c-4.5,7.299-10.4,13.5-17.4,18.299c-7.1,4.9-15.3,8.301-24.1,9.701\n" +
            "\t\tC349.342,704.9,345.943,705.199,342.443,705.199z M515.143,874.199c-5.899,13.201-23.8,22.801-45.1,22.801\n" +
            "\t\tc-21.3,0-39.3-9.6-45.1-22.801c-2.101-4.6,3-9.299,10.1-9.299h70.201C512.243,864.9,517.243,869.5,515.143,874.199z M505.143,839.5\n" +
            "\t\th-70.2c-7.101,0-12.101-4.699-10.101-9.301c4.9-11.1,14-19.6,30.3-22.1c2-0.299,4,0,5.9,0.9l4.5,2.199c2.8,1.4,6.1,1.4,8.9,0\n" +
            "\t\tl4.399-2.199c1.8-0.9,3.901-1.199,5.901-0.9c16.3,2.4,25.399,11,30.3,22.1C517.243,834.9,512.243,839.5,505.143,839.5z\"/>\n" +
            "\t<path d=\"M386.542,665.9c2.1-3.4,2.8-7.701,1.6-11.9c-2-6.9-8.399-11.199-15.199-10.9c-1.101,0.1-2.2,0.201-3.301,0.5\n" +
            "\t\tc-7.1,2-14.699,3.201-22.6,3.5c-1.2,0-2.4,0.1-3.6,0.1c-8.801,0-17.4-1.1-25.301-3.299c-1.399-0.4-2.699-0.801-4-1.201\n" +
            "\t\tc-7.899-2.6-16.3,1.701-18.899,9.5c-2.601,7.9,1.7,16.301,9.5,18.9c9.399,3.1,19.3,5,29.7,5.701c3,0.199,6,0.299,9,0.299\n" +
            "\t\tc6.699,0,13.3-0.5,19.699-1.5c5-0.799,9.9-1.799,14.7-3.199C381.642,671.5,384.642,669.1,386.542,665.9z\"/>\n" +
            "\t<path d=\"M625.643,643.801c-8.199,2.299-17,3.5-26.199,3.5c-10.301,0-20.4-1.602-29.301-4.5c-7.899-2.602-16.3,1.699-18.899,9.5\n" +
            "\t\tc-2.601,7.898,1.7,16.299,9.5,18.898c12.1,4,25.1,6,38.7,6c12,0,23.5-1.6,34.399-4.699c8-2.301,12.601-10.6,10.3-18.5\n" +
            "\t\tC641.943,646.1,633.643,641.5,625.643,643.801z\"/>\n" +
            "</g>\n" +
            "</svg>";
    private static final String logo = "<svg fill=\"#A31ACB\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"70px\" height=\"70px\" viewBox=\"0 0 959.199 959.199\" xml:space=\"preserve\"><g><path d=\"M788.143,259.2c-16.699-44.2-41.399-81.7-73.399-111.5c-32.7-30.5-73-52.8-120-66.5c0,0,0,0-0.101-0.1\n" +
            "\t\tc-52.3-52.3-121.9-81.1-195.9-81.1s-143.5,28.8-195.8,81.1c-52.3,52.3-81.1,121.9-81.1,195.9c0,18.9,1.9,37.5,5.6,55.7\n" +
            "\t\tc-14.3,41.7-20.8,85.9-19.4,132.101c2.4,78.5,27.9,160.899,75.6,244.899c0.1,0.102,0.2,0.301,0.3,0.4\n" +
            "\t\tc18.6,140.6,138.9,249.1,284.6,249.1c128.3,0,237-84.199,273.8-200.398c9.8-7.5,19.101-15.602,27.9-24.5\n" +
            "\t\tc52.3-52.301,81.1-121.9,81.1-195.9c0-50.9-13.6-99.701-39.1-142.201C812.443,370.3,809.843,316.5,788.143,259.2z M597.643,705.199\n" +
            "\t\tc-33.5,0-60.6-27.1-60.6-60.6s27.1-60.6,60.6-60.6s60.601,27.1,60.601,60.6S631.043,705.199,597.643,705.199z M630.843,435.2h145.9\n" +
            "\t\tc16.2,31.601,24.7,66.701,24.7,103.201c0,50-16.101,97.5-45.801,136.699c0-1,0-2.1,0-3.1\n" +
            "\t\tC755.643,573.699,706.143,486.9,630.843,435.2z M762.143,385.2h-20.3c-76.8,0-153.399-1.2-230.3-1.3\n" +
            "\t\tc-55.8,0-112.901,5-163.901,27.7c-83.399,37.1-133.399,117.099-155.1,203c-1.2-2.9-2.3-5.799-3.5-8.699\n" +
            "\t\tc-19.2-49-29.7-96.801-31.1-142.701c-0.5-15.7,0.1-31.1,1.7-46.2c1.5-14,3.9-27.8,7.2-41.3c3.3-13.6,7.5-26.8,12.6-39.8\n" +
            "\t\tc5.8-14.6,12.7-28.9,20.7-42.7c30.8-52.9,77.3-97.7,134.6-129.4c53.399-29.6,113.399-45.9,168.8-45.9c17.6,0,34.6,1.6,50.6,4.9\n" +
            "\t\tc4.7,1,9.4,2,13.9,3.1c14.7,3.6,28.7,8.2,41.8,13.6c14.4,6,27.9,13,40.3,21.2c41.301,26.9,71.7,65.5,90.801,115.6\n" +
            "\t\tc7,18.4,11.699,36.5,14.899,53.1c2.9,14.9,4.5,28.6,5.3,40.2C761.743,375.2,762.043,380.5,762.143,385.2z M398.842,50\n" +
            "\t\tc31.2,0,61.4,6.3,89.3,18.2c-59.3,2.7-121.7,20.9-177.5,51.8c-55,30.5-101.399,71.7-135.8,120.4c7.5-46.7,29.4-89.7,63.5-123.8\n" +
            "\t\tC281.142,73.6,338.142,50,398.842,50z M342.443,705.199c-33.5,0-60.601-27.1-60.601-60.6c0-18.799,8.5-35.5,21.9-46.6\n" +
            "\t\tc6.7-5.5,14.5-9.6,23.1-11.9c5-1.299,10.2-2,15.5-2c3.3,0,6.5,0.301,9.7,0.801c28.9,4.6,50.9,29.6,50.9,59.799\n" +
            "\t\tc0,11.602-3.301,22.5-8.9,31.701c-4.5,7.299-10.4,13.5-17.4,18.299c-7.1,4.9-15.3,8.301-24.1,9.701\n" +
            "\t\tC349.342,704.9,345.943,705.199,342.443,705.199z M515.143,874.199c-5.899,13.201-23.8,22.801-45.1,22.801\n" +
            "\t\tc-21.3,0-39.3-9.6-45.1-22.801c-2.101-4.6,3-9.299,10.1-9.299h70.201C512.243,864.9,517.243,869.5,515.143,874.199z M505.143,839.5\n" +
            "\t\th-70.2c-7.101,0-12.101-4.699-10.101-9.301c4.9-11.1,14-19.6,30.3-22.1c2-0.299,4,0,5.9,0.9l4.5,2.199c2.8,1.4,6.1,1.4,8.9,0\n" +
            "\t\tl4.399-2.199c1.8-0.9,3.901-1.199,5.901-0.9c16.3,2.4,25.399,11,30.3,22.1C517.243,834.9,512.243,839.5,505.143,839.5z\"/><path d=\"M386.542,665.9c2.1-3.4,2.8-7.701,1.6-11.9c-2-6.9-8.399-11.199-15.199-10.9c-1.101,0.1-2.2,0.201-3.301,0.5\n" +
            "\t\tc-7.1,2-14.699,3.201-22.6,3.5c-1.2,0-2.4,0.1-3.6,0.1c-8.801,0-17.4-1.1-25.301-3.299c-1.399-0.4-2.699-0.801-4-1.201\n" +
            "\t\tc-7.899-2.6-16.3,1.701-18.899,9.5c-2.601,7.9,1.7,16.301,9.5,18.9c9.399,3.1,19.3,5,29.7,5.701c3,0.199,6,0.299,9,0.299\n" +
            "\t\tc6.699,0,13.3-0.5,19.699-1.5c5-0.799,9.9-1.799,14.7-3.199C381.642,671.5,384.642,669.1,386.542,665.9z\"/><path d=\"M625.643,643.801c-8.199,2.299-17,3.5-26.199,3.5c-10.301,0-20.4-1.602-29.301-4.5c-7.899-2.602-16.3,1.699-18.899,9.5\n" +
            "\t\tc-2.601,7.898,1.7,16.299,9.5,18.898c12.1,4,25.1,6,38.7,6c12,0,23.5-1.6,34.399-4.699c8-2.301,12.601-10.6,10.3-18.5\n" +
            "\t\tC641.943,646.1,633.643,641.5,625.643,643.801z\"/></g></svg><p style=\"color:#a31acb;text-align:center\">VieSpa</p>";

    public static void print(Transaction transaction) throws Exception {

        BufferedWriter writer = null;
        Customer customer = Customer.getByName(transaction.getCustomer());
        Course course = Course.getByName(transaction.getCourse());

        try {
            writer = new BufferedWriter(new FileWriter("..\\invoices\\invoice_" + transaction.getId().toString() + ".html"));

            String head = String.format("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><title>Invoice - %s </title>",
                    transaction.getId());
            writer.write(head);
            String style = "<style>.container{width:800px;margin:auto;border:2px #000 solid;padding:1em}header{display:flex;justify-content:space-between;align-items:center;border-bottom:2px #000 solid}section{border-bottom:2px #000 solid}.seller>div{display:flex;justify-content:space-between}table{margin-top:1em;width:100%;border-collapse:collapse}td,th{border:1px #000 solid}.sign{display:flex;justify-content:space-around}.sign div:first-child p:first-child{display:block;margin-bottom:8em}</style></head>";
            writer.write(style);
            String header = String.format("<body><div class=\"container\"><header><div> %s </div><div><h1>Hóa đơn dịch vụ</h1><p>Ngày: %s </p></div><div><p>Mẫu số: VIESPA01</p><p style=\"\">Ký hiệu: HD01/23A</p><p>Số: %s </p></div></header>",
                    logo,
                    DateUtil.convert(String.valueOf(transaction.bookingProperty().getValue())),
                    transaction.getId());
            writer.write(header);
            String saler = "<section class=\"seller\"><p>Đơn vị bán hàng: Công ty TNHH nhiều thành viên VieSpa</p><p>Mã số thuế: 00000000000000</p><p>Địa chỉ: Số 265 Đội Cấn - Ba Đình - Hà Nội - Việt Nam</p><div><p>Số điện thoại: 0900000000000</p><p>Số tài khoản: 1234-1234-1234-1234-1234</p></div></section>";
            writer.write(saler);
            String buyer = String.format("<section class=\"buyer\"><p>Họ tên người mua dịch vụ: %s </p><p>Địa chỉ: %s </p><p>Email: %s </p><p>Số điện thoại: %s </p><p>Ghi chú: %s </p></section>",
                    transaction.getCustomer(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getPhone(),
                    transaction.getNote());
            writer.write(buyer);
            String table = String.format("<table><thead><td>Tên dịch vụ</td><td>Đơn giá</td><td>VAT</td><td>Thành tiền</td></thead><tbody><tr><td><h4> %s </h4><p> %s </p></td><td> %s </td><td> %s </td><td> %s </td></tr></tbody></table>",
                    transaction.getCourse(),
                    course.getDescription(),
                    course.getPrice(),
                    Double.parseDouble(course.getPrice()) * 0.1,
                    Double.parseDouble(course.getPrice()) * 1.1);
            writer.write(table);
            String sign = String.format("<section class=\"sign\"><div><p>Người mua hàng</p><p> %s </p></div><div><p>Người bán hàng</p><div> %s </div></div>",
                    transaction.getCustomer(),
                    stamp);
            writer.write(sign);
            String footer = String.format("</section><footer><p>Hóa đơn cung cấp bởi công ty TNHH nhiều thành viên VieSpa - https://VieSpa.com - Đẹp là tất cả</p><p>Tra cứu hóa đơn điện tử tại https://viespa.com/invoice/%s. Mã số bí mật: xxxxxxxxxxx</p></footer></div></body><script>window.print()</script></html>",
                    transaction.getId());
            writer.write(footer);

        } catch (Exception e) {
            throw new Exception("FILE NOT FOUND");
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}