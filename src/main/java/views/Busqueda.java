package views;

import lombok.SneakyThrows;
import org.json.JSONArray;
import views.requestview.RequestView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private JLabel labelAtras;
    private JLabel labelExit;
    private JSONArray jsonHuesped = datostabla("guests");
    private JSONArray jsonReservacion = datostabla("reservations");


    int xMouse, yMouse;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 18));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        listarReservacion(jsonReservacion);


        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);


        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        listarHuesped(jsonHuesped);


        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String busqueda = txtBuscar.getText();
                JSONArray huespedes = new JSONArray();
                JSONArray reservaciones = new JSONArray();

                for (int x = 0; x < jsonReservacion.length(); x++) {
                    if (Objects.equals(busqueda, jsonReservacion.getJSONObject(x).get("entrydate"))) {
                        String idReservacion = String.valueOf(jsonReservacion.getJSONObject(x).get("id"));
                        JSONArray obtenerReservacion = datostabla("reservations/" + idReservacion);
                        reservaciones.putAll(obtenerReservacion);
                        listarReservacion(reservaciones);
                        String idHuesped = String.valueOf(jsonReservacion.getJSONObject(x).get("guest"));
                        JSONArray obtenerHuesped = datostabla("guests/" + idHuesped);
                        huespedes.putAll(obtenerHuesped);
                        listarHuesped(huespedes);

                    } else if (Objects.equals(busqueda, "")) {
                        listarHuesped(jsonHuesped);
                        listarReservacion(jsonReservacion);

                    } else {
                        for (int i = 0; i < jsonHuesped.length(); i++) {
                            if (Objects.equals(busqueda, jsonHuesped.getJSONObject(i).get("document"))) {
                                String idHuesped = String.valueOf(jsonHuesped.getJSONObject(i).get("id"));
                                JSONArray obtenerHuesped = datostabla("guests/" + idHuesped);
                                listarHuesped(obtenerHuesped);
                                listarReservacion(jsonHuesped.getJSONObject(i).getJSONArray("reservations"));
                            }
                        }
                    }
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);


        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tbReservas.getSelectedRow();
            }
        });

        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();

        btnEliminar.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            @Override
            public void mouseClicked(MouseEvent e) {

                if (tbReservas.isFocusOwner()) {
                    int rowSelect = tbReservas.getSelectedRow();
                    Object id = tbReservas.getValueAt(rowSelect, 0);
                    delete(id, "reservations");
                    listarReservacion(jsonReservacion);

                } else if (tbHuespedes.isFocusOwner()) {
                    int rowSelect = tbHuespedes.getSelectedRow();
                    Object id = tbHuespedes.getValueAt(rowSelect, 0);
                    delete(id, "guests");
                    listarReservacion(jsonReservacion);
                    listarHuesped(jsonHuesped);
                }

            }
        });

        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }


    public void delete(Object id, String maping) throws IOException {

        RequestView.conection(maping + '/' + id.toString(), "DELETE", null);
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    public JSONArray datostabla(String mapping) {
        try {
            return (JSONArray) RequestView.conection(mapping, "GET", null).getJsonResponse().get("content");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listarHuesped(JSONArray jsonHuesped) {
        DefaultTableModel modeloHuespedes = new DefaultTableModel();
        modeloHuespedes.setColumnIdentifiers(new Object[]{"No. Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Telefono", "No. Documento"});
        modeloHuespedes.setRowCount(0);

        for (int i = 0; i < jsonHuesped.length(); i++) {

            modeloHuespedes.addRow(new Object[]{
                    jsonHuesped.getJSONObject(i).getLong("id"),
                    jsonHuesped.getJSONObject(i).getString("name"),
                    jsonHuesped.getJSONObject(i).getString("lastname"),
                    jsonHuesped.getJSONObject(i).getString("birthday"),
                    jsonHuesped.getJSONObject(i).getString("country"),
                    jsonHuesped.getJSONObject(i).getString("phone"),
                    jsonHuesped.getJSONObject(i).getString("document")
            });
        }
        tbHuespedes.setModel(modeloHuespedes);
    }

    public void listarReservacion(JSONArray jsonReservacion) {
        DefaultTableModel modeloReservaciones = new DefaultTableModel();

        modeloReservaciones.setColumnIdentifiers(new Object[]{"Numero de Reserva", "Fecha Check In", "Fecha Check Out", "Valor", "Forma de Pago", "No. de Huesped"});
        modeloReservaciones.setRowCount(0);


        for (int i = 0; i < jsonReservacion.length(); i++) {

            modeloReservaciones.addRow(new Object[]{
                    jsonReservacion.getJSONObject(i).getLong("id"),
                    jsonReservacion.getJSONObject(i).getString("entrydate"),
                    jsonReservacion.getJSONObject(i).getString("outdate"),
                    jsonReservacion.getJSONObject(i).getInt("price"),
                    jsonReservacion.getJSONObject(i).getString("payform"),
                    jsonReservacion.getJSONObject(i).get("guest")

            });
        }
        tbReservas.setModel(modeloReservaciones);
    }
}
