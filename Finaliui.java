import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Finaliui extends JFrame {
    private Connection connection;
    private JTextField stuId, name, tel, dept, priceField;
    private ResultSet resultSet;

    public Finaliui() {
        // GUI 초기화
        setTitle("데이터베이스 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // 데이터베이스 연결
        connection = makeConnection();
        // 데이터베이스에서 초기 데이터 가져오기
        fetchDataFromDatabase();

        // 텍스트 필드 초기화
        stuId = new JTextField(10);
        name = new JTextField(20);
        tel = new JTextField(15);
        dept = new JTextField(4);
        priceField = new JTextField(10);

        // 텍스트 필드를 편집할 수 없도록 설정
        stuId.setEditable(false);
        name.setEditable(false);
        tel.setEditable(false);
        dept.setEditable(false);
        priceField.setEditable(false);

        // "이전" 버튼 및 이벤트 처리
        JButton beforeButton = new JButton("Previous");
        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousData();
            }
        });

        // "다음" 버튼 및 이벤트 처리
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextData();
            }
        });

        // "삽입" 버튼 및 이벤트 처리
        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });

        // "삭제" 버튼 및 이벤트 처리
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        // "검색" 버튼 및 이벤트 처리
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchData();
            }
        });

        // "지우기" 버튼 및 이벤트 처리
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // 레이아웃 설정
        setLayout(new GridLayout(7, 2));
        add(new JLabel("stuID:"));
        add(stuId);
        add(new JLabel("name:"));
        add(name);
        add(new JLabel("tel:"));
        add(tel);
        add(new JLabel("dept:"));
        add(dept);
        add(new JLabel("Price:"));
        add(priceField);
        add(beforeButton);
        add(nextButton);
        add(insertButton);
        add(deleteButton);
        add(searchButton);
        add(clearButton);

        // 프로그램 시작 시 초기 데이터 표시
        showCurrentData();
    }

    private void fetchDataFromDatabase() {
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = stmt.executeQuery("SELECT * FROM books");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 가져오기 실패");
        }
    }

    private void showCurrentData() {
        try {
            if (resultSet.next()) {
                stuId.setText(resultSet.getString("book_id"));
                name.setText(resultSet.getString("title"));
                tel.setText(resultSet.getString("publisher"));
                dept.setText(resultSet.getString("year"));
                priceField.setText(resultSet.getString("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 표시 실패");
        }
    }

    private void showPreviousData() {
        try {
            if (resultSet.previous()) {
            	stuId.setText(resultSet.getString("book_id"));
                name.setText(resultSet.getString("title"));
                tel.setText(resultSet.getString("publisher"));
                dept.setText(resultSet.getString("year"));
                priceField.setText(resultSet.getString("price"));
            } else {
                resultSet.first(); // 첫 번째 레코드로 이동
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "이전 데이터 표시 실패");
        }
    }

    private void showNextData() {
        try {
            if (resultSet.next()) {
                stuId.setText(resultSet.getString("book_id"));
                name.setText(resultSet.getString("title"));
                tel.setText(resultSet.getString("publisher"));
                dept.setText(resultSet.getString("year"));
                priceField.setText(resultSet.getString("price"));
            } else {
                resultSet.last(); // 마지막 레코드로 이동
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "다음 데이터 표시 실패");
        }
    }

    private void insertData() {
        // 사용자로부터 입력 값을 가져옴
        String title = JOptionPane.showInputDialog(this, "Enter Title:");
        String publisher = JOptionPane.showInputDialog(this, "Enter Publisher:");
        String year = JOptionPane.showInputDialog(this, "Enter Year:");
        String price = JOptionPane.showInputDialog(this, "Enter Price:");

        try {
            // 새로운 데이터베이스 레코드 삽입
            String insertQuery = "INSERT INTO Students (title, publisher, year, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                pstmt.setString(1, title);
                pstmt.setString(2, publisher);
                pstmt.setString(3, year);
                pstmt.setString(4, price);
                pstmt.executeUpdate();
            }

            // 삽입 후에 데이터 다시 불러오기
            fetchDataFromDatabase();
            showCurrentData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 삽입 실패");
        }
    }

    private void deleteData() {
        try {
            // 현재 표시된 데이터를 데이터베이스에서 삭제
            String deleteQuery = "DELETE FROM Students WHERE book_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
                pstmt.setString(1, stuId.getText());
                pstmt.executeUpdate();
            }

            // 삭제 후에 데이터 다시 불러오기
            fetchDataFromDatabase();
            showCurrentData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 삭제 실패");
        }
    }

    private void searchData() {
        // TODO: 검색 기능 구현
    }

    private void clearFields() {
        stuId.setText("");
        name.setText("");
        tel.setText("");
        dept.setText("");
        priceField.setText("");
    }

    private Connection makeConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/Duksung";
            String id = "root";
            String password = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, id, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터베이스 연결 실패");
        }
        return con;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Finaliui().setVisible(true);
            }
        });
    }
}
