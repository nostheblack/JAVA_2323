import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GuiDatabase extends JFrame {
    private Connection connection;
    private JTextField idField, titleField, publisherField, yearField, priceField;
    private ResultSet resultSet;

    public GuiDatabase() {
        // GUI 초기화
        setTitle("Database GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // 데이터베이스 연결
        connection = makeConnection();
        // 데이터베이스에서 초기 데이터 가져오기
        fetchDataFromDatabase();

        // 텍스트 필드 초기화
        idField = new JTextField(10);
        titleField = new JTextField(20);
        publisherField = new JTextField(15);
        yearField = new JTextField(4);
        priceField = new JTextField(10);

        // 텍스트 필드를 편집할 수 없도록 설정
        idField.setEditable(false);
        titleField.setEditable(false);
        publisherField.setEditable(false);
        yearField.setEditable(false);
        priceField.setEditable(false);

        // "before" 버튼 및 이벤트 처리
        JButton beforeButton = new JButton("Before");
        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousData();
            }
        });

        // "next" 버튼 및 이벤트 처리
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextData();
            }
        });

        // "Insert" 버튼 및 이벤트 처리
        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });

        // "Delete" 버튼 및 이벤트 처리
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        // "Search" 버튼 및 이벤트 처리
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchData();
            }
        });

        // "Clear" 버튼 및 이벤트 처리
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // 레이아웃 설정
        setLayout(new GridLayout(7, 2));
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Publisher:"));
        add(publisherField);
        add(new JLabel("Year:"));
        add(yearField);
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

    private Connection makeConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/book_db";
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
                idField.setText(resultSet.getString("book_id"));
                titleField.setText(resultSet.getString("title"));
                publisherField.setText(resultSet.getString("publisher"));
                yearField.setText(resultSet.getString("year"));
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
                idField.setText(resultSet.getString("book_id"));
                titleField.setText(resultSet.getString("title"));
                publisherField.setText(resultSet.getString("publisher"));
                yearField.setText(resultSet.getString("year"));
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
                idField.setText(resultSet.getString("book_id"));
                titleField.setText(resultSet.getString("title"));
                publisherField.setText(resultSet.getString("publisher"));
                yearField.setText(resultSet.getString("year"));
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
            String insertQuery = "INSERT INTO books (title, publisher, year, price) VALUES (?, ?, ?, ?)";
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
            String delete

