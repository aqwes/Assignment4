package com.MonitorStud;

import com.Logic.Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * The GUI for assignment 4
 */
public class GUIMonitor extends Component implements ActionListener {
    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     */
    private JFrame frame;                // The Main window
    private JMenu fileMenu;                // The menu
    private JMenuItem openItem;            // File - open
    private JMenuItem saveItem;            // File - save as
    private JMenuItem exitItem;            // File - exit
    private JTextField txtFind;            // Input string to find
    private JTextField txtReplace;        // Input string to replace
    private JCheckBox chkNotify;        // User notification choise
    private JLabel lblInfo;                // Hidden after file selected
    private JButton btnCreate;            // Start copying
    private JButton btnClear;            // Removes dest. file and removes marks
    private JLabel lblChanges;            // Label telling number of replacements
    private Controller controller;
    private JFileChooser fileChooser;
    private File file;
    private String txtFile;
    private JTextPane txtPaneSource;
    private Highlighter hilite;

    /**
     * Constructor
     */
    public GUIMonitor() {
    }

    /**
     * Starts the application
     *
     * @param controller
     */
    public void Start(Controller controller) {
        this.controller = controller;
        frame = new JFrame();
        frame.setBounds(0, 0, 714, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Text File Copier - with Find and Replace");
        InitializeGUI();                    // Fill in components
        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void InitializeGUI() {
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open Source File");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.addActionListener(e -> open());
        saveItem = new JMenuItem("Save Destination File As");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.setEnabled(false);
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });


        exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        JMenuBar bar = new JMenuBar();
        frame.setJMenuBar(bar);
        bar.add(fileMenu);


        JPanel pnlFind = new JPanel();
        pnlFind.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Find and Replace"));
        pnlFind.setBounds(12, 32, 436, 122);
        pnlFind.setLayout(null);
        frame.add(pnlFind);
        JLabel lab1 = new JLabel("Find:");
        lab1.setBounds(7, 30, 80, 13);
        pnlFind.add(lab1);
        JLabel lab2 = new JLabel("Replace with:");
        lab2.setBounds(7, 63, 80, 13);
        pnlFind.add(lab2);

        txtFind = new JTextField();

        txtFind.setBounds(88, 23, 327, 20);
        pnlFind.add(txtFind);
        txtFind.setText("");
        txtReplace = new JTextField();
        txtReplace.setBounds(88, 60, 327, 20);
        txtReplace.setText("");
        pnlFind.add(txtReplace);
        chkNotify = new JCheckBox("Notify user on every match");
        chkNotify.setBounds(88, 87, 180, 17);
        pnlFind.add(chkNotify);

        lblInfo = new JLabel("Select Source File..");
        lblInfo.setBounds(485, 42, 120, 13);
        frame.add(lblInfo);

        btnCreate = new JButton("Copy to Destination");
        btnCreate.setBounds(465, 119, 230, 23);


        frame.add(btnCreate);
        btnClear = new JButton("Clear dest. and remove marks");
        btnClear.setBounds(465, 151, 230, 23);

        frame.add(btnClear);

        lblChanges = new JLabel("No. of Replacements:");
        lblChanges.setBounds(279, 161, 200, 13);
        frame.add(lblChanges);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(12, 170, 653, 359);
        frame.add(tabbedPane);
        txtPaneSource = new JTextPane();

        JScrollPane scrollSource = new JScrollPane(txtPaneSource);
        tabbedPane.addTab("Source", null, scrollSource, null);
        JTextPane txtPaneDest = new JTextPane();
        JScrollPane scrollDest = new JScrollPane(txtPaneDest);
        tabbedPane.addTab("Destination", null, scrollDest, null);

        btnClear.addActionListener(this);
        btnCreate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreate) {
            if (!txtFind.getText().isEmpty() && !txtReplace.getText().isEmpty()) {
                int reply = JOptionPane.showConfirmDialog(null, "Replace " + txtFind.getText() + " with " + txtReplace.getText() + ".", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {


                    highlight(txtPaneSource, txtFind.getText());

                }
            } else if (txtFind.getText().isEmpty() || txtReplace.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in both fields");

            }
        }
        if (e.getSource() == btnClear) {
            txtPaneSource.setText("");
            txtFind.setText("");
            txtReplace.setText("");


        }
    }

    private void open() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt", "txt");
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();

            txtFile = file.toString();
            controller.readFile(txtFile);

            txtPaneSource.setText(controller.getFileText());
            controller.run();

        }

    }

    public String getTxtFind() {
        return txtFind.getText();
    }

    public String getTxtReplace() {
        return txtReplace.getText();
    }

    public JTextPane getTxtPaneSource() {
        return txtPaneSource;
    }

    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter() {
            super(Color.green);

        }
    }

    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter();

    public void highlight(JTextPane txtPaneSource, String pattern) {

        try {
            hilite = txtPaneSource.getHighlighter();
            Document doc = txtPaneSource.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;
            while ((pos = text.toUpperCase().indexOf(pattern.toUpperCase(), pos)) >= 0) {
                hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);

                pos += pattern.length();
                System.out.println(pattern.toString());


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


