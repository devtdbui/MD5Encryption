
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author devtd
 */
public class Validation {

    Scanner sc = new Scanner(System.in);

    public int getChoice() {
        while (true) {
            System.out.print("Selection of users: ");
            String input = sc.nextLine();
            Pattern p = Pattern.compile("^[1-3]$");
            if (p.matcher(input).find()) {
                int choice = Integer.parseInt(input);
                return choice;
            } else {
                System.out.print("invalid choice, pls re-enter");
            }
        }
    }

    public String checkString() {
        String account = "";
        while (true) {
            account = sc.nextLine();
            if (!account.trim().equals("")) {
                return account;
            } else {
                System.err.println("empty string, please input again");
            }
        }
    }

    public String getEmail() {
        String mail = "";
        while (true) {
            mail = sc.nextLine();
            Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$");
            if (p.matcher(mail).find()) {
                break;
            } else {
                System.err.println("Invalid mail, please input again");
            }
        }
        return mail;
    }

    public String getDate() {
        SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        while (true) {
            try {
                date = dfInput.parse(sc.nextLine());
                return dfInput.format(date);
            } catch (Exception e) {
                System.err.println("invalid date, please input again");
            }
        }
    }

    public String getPhone() {
        String phone = "";
        while (true) {
            phone = sc.nextLine();
            Pattern p = Pattern.compile("^[0-9]{10,11}$");
            if (p.matcher(phone).find()) {
                break;
            } else {
                System.err.println("Invalid phone, please input again");
            }
        }
        return phone;
    }

    private String checkInputUsername(ArrayList<Account> list) {
        while (true) {
            String result = checkString();
            for (int i = 0; i < list.size(); i++) {
                if (result.equalsIgnoreCase(list.get(i).getUsername())) {
                    System.err.println("Username exist!!!");
                }
            }
            return result;
        }
    }

    /*
Để tính giá trị băm mật mã trong Java, MessageDigest Class được sử dụng, trong gói java.security.

Lớp MessagDigest cung cấp hàm băm mật mã sau để tìm giá trị băm của văn bản, đó là:
1. MD5
2. SHA-1
3. SHA-256

Thuật toán này được khởi tạo trong phương thức tĩnh gọi là getInstance () . Sau khi chọn thuật toán, nó tính toán giá trị digest và trả về kết quả theo mảng byte.

Lớp BigInteger được sử dụng, nó chuyển đổi mảng byte kết quả thành biểu diễn cường độ ký hiệu của nó .

Đại diện này chuyển đổi sang định dạng hex để nhận MessageDigest
    
     */
    String MD5Encryption(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
