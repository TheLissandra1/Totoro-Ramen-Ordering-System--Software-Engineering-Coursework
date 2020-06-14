import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Title : ViewStatistics.java
 * Description: This class is Statics page JFrame;
 * @author Zhaoxiao Su
 * @version 1.0
 */
public class ViewStatistics extends Interface {

    JPanel panel; // Main JPanel
    JButton button; // back button
    Menu menu = new Menu();

    public ViewStatistics() {
        super();

        // set background
        showFrame(7);
        ImageIcon imageIcon = background;

        panel = new BackGroundJPanel(imageIcon.getImage());
        this.setLayout(new GridLayout(1, 1));
        this.add(panel);

        JTable table = createTable();
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds((this.getWidth() - 1100) / 2, 115, 1100, 579);
        panel.add(scrollPane);
        panel.setLayout(null);
        table.setRowHeight(35);

        this.button = new JButton("Previous Page");
        button.setBounds((scrollPane.getX() + scrollPane.getWidth() - 180),
                (scrollPane.getY() + scrollPane.getHeight() + 10),
                180, 40);
        button.setBackground(Color.WHITE);
        panel.add(button);

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HelperControl helperControl = new HelperControl();
                helperControl.skipManagerOptions();
            }
        });

        this.setVisible(true);
    }


    public static void main(String[] args) {
        new ViewStatistics();
    }

    public JTable createTable() {
        String columns[] = {"","","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        WeekData weekData = new WeekData();
        Object data[][] = new Object[11][columns.length];
        data[0][0] = menu.spicy[0];
        String spi[] = new String[menu.spicy.length-1];

        for (int i=0;i<spi.length;i++){
            spi[i] = menu.spicy[i+1];
        }
        data[0][1] = spi;
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = "";
            }
        }

        data[1][0] = menu.dishes[0][0];
        String soup[] = new String[menu.dishes[0].length-1];
        for (int i=0;i<soup.length;i++){
            soup[i] = menu.dishes[0][i+1];
        }
        data[1][1] = soup;

        data[2][0] = menu.dishes[1][0];
        String noodles[] = new String[menu.dishes[1].length-1];
        for (int i=0;i<noodles.length;i++){
            noodles[i] = menu.dishes[1][i+1];
        }
        data[2][1] = noodles;

        data[3][0] = menu.dishes[2][0];
        String spring[] = new String[menu.dishes[2].length-1];
        for (int i=0;i<spring.length;i++){
            spring[i] = menu.dishes[2][i+1];
        }
        data[3][1] = spring;

        java.util.List<Integer> list = new ArrayList<>();

        for (int i = 0;i< 6;i++){
            list.add(0);
        }
        for (int i = 2; i < data[0].length; i++) {
            data[0][i] = weekData.list.get(i - 2).spiciness;
            for (int j = 0;j< 6;j++){
                list.set(j,list.get(j)+weekData.list.get(i - 2).spiciness[j]);
            }

            data[1][i] = weekData.list.get(i - 2).soup;
            data[2][i] = weekData.list.get(i - 2).noddles;
            data[3][i] = weekData.list.get(i - 2).springOnion;
            data[4][i] = weekData.list.get(i - 2).extraNori;
            data[5][i] = weekData.list.get(i - 2).extraBoiledEgg;
            data[6][i] = weekData.list.get(i - 2).bambooShoots;
            data[7][i] = weekData.list.get(i - 2).extraChashu;
            data[8][i] = weekData.list.get(i - 2).nori;
            data[9][i] = weekData.list.get(i - 2).chashu;
            data[10][i] = weekData.list.get(i - 2).boiledEgg;
        }

        int index = 0;
        for (int i = 0;i< 6;i++){
            if (list.get(i) > index){
                index = i;
            }
        }
       JOptionPane.showMessageDialog(this,"Most people order "+index+" spiciness");


        data[4][0] = menu.addOns[0][0];
        data[5][0] = menu.addOns[1][0];
        data[6][0] = menu.addOns[2][0];
        data[7][0] = menu.addOns[3][0];
        data[8][0] = menu.dishes[3][0];
        data[9][0] = menu.dishes[4][0];
        data[10][0] = menu.dishes[5][0];

        AbstractTableModel tableModel = new AbstractTableModel() {
            public String getColumnName(int col) {
                return columns[col].toString();
            }

            public Class getColumnClass(int col) {
                if (getRowCount() < 1)
                    return null;
                return data[0][col].getClass();
            }

            public int getRowCount() {
                return data.length;
            }

            public int getColumnCount() {
                return columns.length;
            }

            public Object getValueAt(int row, int col) {
                return data[row][col];
            }

            public boolean isCellEditable(int row, int col) {
                return true;
            }

            public void setValueAt(Object value, int row, int col) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        };

        TableCellRenderer jTableCellRenderer = new TableCellRenderer() {
            private int minHeight = -1;
            private int currHeight = -1;

            public Component getTableCellRendererComponent(JTable table,
                                                           Object value,
                                                           boolean isSelected,
                                                           boolean hasFocus,
                                                           int row, int column) {

                if (!value.getClass().isArray()) {
                    Component component = table.getDefaultRenderer(value.getClass())
                            .getTableCellRendererComponent(table, value,
                                    isSelected, hasFocus, row, column);
                    DefaultTableCellRenderer r = (DefaultTableCellRenderer) component;
                    r.setHorizontalAlignment(JLabel.CENTER);
                    table.setDefaultRenderer(Object.class, r);
                    if (column == 0) {
                        r.setFont(new Font("Microsoft Yahei", Font.PLAIN, 18));
                    }
                    return component;
                } else {
                    Object[] passed = (Object[]) value;
                    if (minHeight == -1) {
                        minHeight = 20;
                    }
                    if (currHeight != passed.length * minHeight) {
                        currHeight = passed.length * minHeight - 1;
                        table.setRowHeight(row, currHeight);
                    }

                    JTable tmpTable = new JTable(new AbstractTableModel() {
                        public int getColumnCount() {
                            return 1;
                        }
                        public int getRowCount() {
                            return passed.length;
                        }
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            return passed[rowIndex];
                        }
                        public boolean isCellEditable(int row, int col) {
                            return true;
                        }
                    });
                    tmpTable.setRowHeight(20);

                    DefaultTableCellRenderer r = new DefaultTableCellRenderer();
                    r.setHorizontalAlignment(JLabel.CENTER);
                    tmpTable.setDefaultRenderer(Object.class, r);
                    return tmpTable;
                }
            }
        };

        // set table style
        JTable table = new JTable();
        table.setModel(tableModel);
        TableColumnModel tcm = table.getColumnModel();
        for (int it = 0; it < tcm.getColumnCount(); it++) {
            tcm.getColumn(it).setCellRenderer(jTableCellRenderer);
        }

        // set font align = center
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        // tableHeader height
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 35));
        tableHeader.setOpaque(false);
        tableHeader.setBackground(Color.WHITE);

        tableHeader.setFont(new Font("Microsoft Yahei", Font.PLAIN, 18));

        return table;
    }

    // BackGround picture class
    private class BackGroundJPanel extends JPanel {
        Image img;

        public BackGroundJPanel(ImageIcon icon) {
            this.img = icon.getImage();
        }

        public BackGroundJPanel(Image img) {
            this.img = img;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
