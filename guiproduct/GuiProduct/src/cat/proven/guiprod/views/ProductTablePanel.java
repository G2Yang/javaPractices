
package cat.proven.guiprod.views;

import cat.proven.guiprod.model.Model;
import cat.proven.guiprod.model.Product;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose
 */
public class ProductTablePanel extends JPanel {

    private final List<Product> products;
    private final String[] columnNames = {
        "Id",
        "Code",
        "Description",
        "Price",
        "Stock"
    };
        
    public ProductTablePanel() {
        products = Model.productService.findAll();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        //
        ProductTableModel tableModel = new ProductTableModel();
        for (String s: columnNames) {
            tableModel.addColumn(s);
        }
        products.forEach((t) -> {
            Object [] o = new Object[columnNames.length];
            o[0] = t.getId();
            o[1] = t.getCode();
            o[2] = t.getDescription();
            o[3] = t.getPrice();
            o[4] = t.getStock();
            tableModel.insertRow(0, o);
        });
        //
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(getEventInfo(e));
                Product p = rowToProduct(e);
                Model.productService.modify(p, p);
            }
            
            private Product rowToProduct(TableModelEvent e) {
                int row = e.getFirstRow();
                Long id = (Long) tableModel.getValueAt(row, 0);
                String code = (String) tableModel.getValueAt(row, 1);
                String description = (String) tableModel.getValueAt(row, 2);
                Double price = (Double) tableModel.getValueAt(row, 3);
                Integer stock = (Integer) tableModel.getValueAt(row, 4);
                return new Product(id, code, description, price, stock);
            }
            
            private String getEventInfo(TableModelEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("TableModelListener{");
                sb.append(String.format("[firstRow:%d]", e.getFirstRow()));
                sb.append(String.format("[lastRow:%d]", e.getLastRow()));
                sb.append(String.format("[column:%d]", e.getColumn()));
                sb.append(String.format("[source:%s]", e.getSource()));
                sb.append(String.format("[type:%d]", e.getType()));
                sb.append("}");
                return sb.toString();
            }
        });
        //
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        //
        JScrollPane scrollPane = new JScrollPane(table);
        //
        add(scrollPane, BorderLayout.CENTER);
    }

    private static class ProductTableModel extends DefaultTableModel {

        public ProductTableModel() {
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            boolean edit = false;
            if (col < 2) {
                edit = false;
            } else {
                edit = true;
            }
            return edit;
        }
        
        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            super.addTableModelListener(l);
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            super.removeTableModelListener(l);
        }
    }

    private static class ProductTableModelMap extends DefaultTableModel {
//    private static class ProductTableModelMap extends AbstractTableModel {

        private final String[] columnNames = {
            "Id",
            "Code",
            "Description",
            "Price",
            "Stock"
        };
        
        private List<Map<String, Object>> data;
        
        public List<Map<String, Object>> getData() {
            return data;
        }
                
        public void setData(List<Product> products) {
            data = new ArrayList<>();
            products.forEach((t) -> {
                Map<String, Object> rowData = new HashMap<>();
                rowData.put(columnNames[0], t.getId());
                rowData.put(columnNames[1], t.getCode());
                rowData.put(columnNames[2], t.getDescription());
                rowData.put(columnNames[3], t.getPrice());
                rowData.put(columnNames[4], t.getStock());
                data.add(rowData);
            });
        }
        
        public ProductTableModelMap() {
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
           return columnNames[col];
        }

        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            boolean edit = false;
            if (col < 2) {
                edit = false;
            } else {
                edit = true;
            }
            return edit;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(columnNames[col]);
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data.get(row).put(columnNames[col], value);
            //Notifies all listeners that the value of the cell at 
            //[row, column] has been updated.
            fireTableCellUpdated(row, col);
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            super.addTableModelListener(l);
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            super.removeTableModelListener(l);
        }
    }
    
}
