import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class TCPCharacterStreameXtfwlcJ {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2208;

        // Mã sinh viên + mã câu hỏi
        String studentCode = "B22DCCN173";
        String qCode = "eXtfwlcJ";
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000);  // set Time out

            // luồng gửi
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            // luồng nhận
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // a) Gửi chuỗi studentCode;qCode
            writer.write(message);  // đẩy chuỗi vào bộ đệm buffer của Buffered
            writer.newLine(); // thêm kí tự xuống dòng vào bộ đệm, khi giao tiếp qua socket, server thường đọc đến dấu xuống dòng để biết đã hết 1 thông điệp, nên phải 
            writer.flush(); // xả toàn bộ buffer xuống stream
            System.out.println("Đã gửi: " + message);

            // b) Nhận danh sách tên miền từ server
            String domainsLine = reader.readLine();  // đọc data nhận
            if (domainsLine == null) {
                System.out.println("Không nhận được dữ liệu từ server.");
                return;
            }
            System.out.println("Danh sách tên miền nhận được:");
            System.out.println(domainsLine);

            // c) Tìm tên miền .edu
            String[] domains = domainsLine.split(",\\s*"); //xử lý data nhận thành chuỗi
            List<String> eduDomains = new ArrayList<>();
            for (String d : domains) {  // đọc nhận và lấy những data có edu
                if (d.trim().endsWith(".edu")) {
                    eduDomains.add(d.trim());
                }
            }

            // Gửi danh sách .edu lên server
            if (eduDomains.isEmpty()) {
                writer.write("Không có tên miền .edu");
            } else {
                // Ghép danh sách thành chuỗi
                String eduList = String.join(", ", eduDomains);
                writer.write(eduList);  // gửi lại qua writer
            }
            writer.newLine();
            writer.flush();
            System.out.println("Đã gửi danh sách .edu: " + eduDomains);

            // d) Đóng kết nối (try-with-resources sẽ tự đóng)
            System.out.println("Kết thúc chương trình.");

        } catch (SocketTimeoutException e) {
            System.out.println("Hết thời gian chờ server (5s).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
