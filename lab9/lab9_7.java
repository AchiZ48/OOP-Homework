package lab9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;

public class lab9_7 extends JFrame {
    private JTextField tfId = new JTextField();
    private JTextField tfName = new JTextField();
    private JTextField tfSurname = new JTextField();
    private JButton btSave = new JButton("Save");
    private JButton btLoad = new JButton("Load");
    private JTable table;
    private DefaultTableModel model;
    private File db = new File("accounts.csv");

    public lab9_7() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(3,2,8,8));
        form.setBorder(BorderFactory.createTitledBorder("Account Owner"));
        form.add(new JLabel("Account ID"));
        form.add(tfId);
        form.add(new JLabel("Name"));
        form.add(tfName);
        form.add(new JLabel("Surname"));
        form.add(tfSurname);
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controls.add(btLoad);
        controls.add(btSave);
        model = new DefaultTableModel(new String[]{"ID","Name","Surname"},0);
        table = new JTable(model);
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
        btSave.addActionListener(e -> save());
        btLoad.addActionListener(e -> load());
        setTitle("Bank Account Registry");
        setSize(520,360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void save() {
        String id = tfId.getText().trim();
        String name = tfName.getText().trim();
        String surname = tfSurname.getText().trim();
        if (id.isEmpty()||name.isEmpty()||surname.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please fill all fields");
            return;
        }
        try (FileWriter fw = new FileWriter(db, true)) {
            fw.write(id + "," + escape(name) + "," + escape(surname) + "\n");
            fw.flush();
            model.addRow(new String[]{id,name,surname});
            tfId.setText(""); tfName.setText(""); tfSurname.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Save failed: "+ex.getMessage());
        }
    }

    private void load() {
        model.setRowCount(0);
        if (!db.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(db))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] a = parseCSV(line);
                if (a.length>=3) model.addRow(new String[]{a[0],a[1],a[2]});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Load failed: "+ex.getMessage());
        }
    }

    private String escape(String s){ return s.replace("\\","\\\\").replace(",","\\,"); }
    private String[] parseCSV(String line){
        ArrayList<String> out=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        boolean esc=false;
        for(char c: line.toCharArray()){
            if(esc){ sb.append(c); esc=false; }
            else if(c=='\\'){ esc=true; }
            else if(c==','){ out.add(sb.toString()); sb.setLength(0); }
            else sb.append(c);
        }
        out.add(sb.toString());
        return out.toArray(new String[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new lab9_7().setVisible(true));
    }
}

class Account {
    private String id;
    private String name;
    private String surname;
    public Account(String id,String name,String surname){this.id=id;this.name=name;this.surname=surname;}
    public String getId(){return id;}
    public String getName(){return name;}
    public String getSurname(){return surname;}
}

