/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.domparserdemo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nam Nguyen
 */
public class XMLAPIHandle {

    public static void main(String[] args) throws Exception {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("username");
        tableModel.addColumn("fullname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Password");
        tableModel.addColumn("Birthday");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Avatar");
        tableModel.addColumn("Status");
        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Show API");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        scrollPane.setBounds(10, 10, 750, 300);
        frame.add(scrollPane);
        System.out.println("Start getting");
        String url = "https://youtube-api-challenger.appspot.com/xml/members";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(con.getInputStream());
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("Member");
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("-----------------");
            Node node = nodeList.item(i);
            Element element = (Element) node;
            System.out.println("ID: " + element.getAttribute("id"));
            System.out.println("UserName: " + element.getElementsByTagName("UserName").item(0).getTextContent());
            System.out.println("FullName: " + element.getElementsByTagName("FullName").item(0).getTextContent());
            System.out.println("Email: " + element.getElementsByTagName("Email").item(0).getTextContent());
            System.out.println("Password: " + element.getElementsByTagName("Password").item(0).getTextContent());
            System.out.println("Birthday: " + element.getElementsByTagName("Birthday").item(0).getTextContent());
            System.out.println("Gender: " + element.getElementsByTagName("Gender").item(0).getTextContent());
            System.out.println("Avatar: " + element.getElementsByTagName("Avatar").item(0).getTextContent());
            System.out.println("Status: " + element.getElementsByTagName("Status").item(0).getTextContent());

            Object str[] = new Object[]{
                element.getAttribute("id"),
                element.getElementsByTagName("UserName").item(0).getTextContent(),
                element.getElementsByTagName("FullName").item(0).getTextContent(),
                element.getElementsByTagName("Email").item(0).getTextContent(),
                element.getElementsByTagName("Password").item(0).getTextContent(),
                element.getElementsByTagName("Birthday").item(0).getTextContent(),
                element.getElementsByTagName("Gender").item(0).getTextContent(),
                element.getElementsByTagName("Avatar").item(0).getTextContent(),
                element.getElementsByTagName("Status").item(0).getTextContent()};
            tableModel.addRow(str);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
