/**
 * <p>
 * @author Administrator
 * @author Modified by Pedro Antonio Gutiérrez and Juan Carlos Fernández (University of Córdoba) 23/10/2008
 * @version 1.0
 * @since JDK1.5
 * </p>
 */
package keel.GraphInterKeel.datacf.help;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.tree.*;

public class HelpFrame extends JFrame {

    /**
     * <p>
     * This is the frame that shows help of KEEL
     * </p>
     */

    // Main contents panel
    private JPanel contentPane;

    // Border layout
    private BorderLayout borderLayout1 = new BorderLayout();

    // JSplitPanel
    private JSplitPane jSplitPane1 = new JSplitPane();

    // Help options
    private HelpOptions options;

    // Help content
    protected HelpContent content;

    // Help themes
    protected Vector themes = new Vector();

    /**
     * <p>
     * Constructor that initializes the frame
     * </p>
     */
    public HelpFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Init the components
     * </p>
     * @throws java.lang.Exception Exception in the component initialization
     */
    private void jbInit() throws Exception {
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("/keel/GraphInterKeel/resources/ico/logo/logo.png")));
        this.setResizable(false);
        this.setSize(new Dimension(800, 600));
        this.setTitle("Help");
        jSplitPane1.setDividerSize(7);
        contentPane.setFont(new java.awt.Font("Arial", 0, 11));
        contentPane.add(jSplitPane1, BorderLayout.CENTER);
        jSplitPane1.setDividerLocation(175);

        // Create tree and help page
        options = new HelpOptions(this);
        content = new HelpContent();

        // Add elements to panel
        jSplitPane1.setLeftComponent(options);
        jSplitPane1.setRightComponent(content);

        content.muestraURL(this.getClass().getResource("/help/intro.html"));

        // Fill help information
        crear_nodos(options.top);
        options.tree.expandRow(0);
    }

    /**
     * <p>
     * Overriden so we can close the frame
     * </p>
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.setVisible(false);
        }
    }

    /**
     * <p>
     * Init nodes for tree help
     * </p>
     * @param top DefaultMutableTreeNode representing the top node of Tree
     */
    public void crear_nodos(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode categ = null;
        DefaultMutableTreeNode categ2 = null;
        DefaultMutableTreeNode categ3 = null;
        DefaultMutableTreeNode hoja = null;

        hoja = new DefaultMutableTreeNode(new HelpSheet("Introduction",
                this.getClass().getResource("/help/intro.html")));
        top.add(hoja);
        themes.add(new HelpSheet("Introduction",
                this.getClass().getResource("/help/intro.html")));


        /*
        //Data file formats
        categ = new DefaultMutableTreeNode("Data file formats");
        top.add(categ);
        hoja = new DefaultMutableTreeNode(new HelpSheet("KEEL format",
        this.getClass().getResource("/help/keel_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("KEEL format",
        this.getClass().getResource("/help/keel_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("CSV format",
        this.getClass().getResource("/help/csv_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("CSV format",
        this.getClass().getResource("/help/csv_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("TXT and TSV format",
        this.getClass().getResource("/help/txt_tsv_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("TXT and TSV format",
        this.getClass().getResource("/help/txt_tsv_format.html")));

        hoja = new DefaultMutableTreeNode(new HelpSheet("PRN format",
        this.getClass().getResource("/help/prn_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("PRN format",
        this.getClass().getResource("/help/prn_format.html")));

        hoja = new DefaultMutableTreeNode(new HelpSheet("C4.5 format",
        this.getClass().getResource("/help/uci_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("C45 format",
        this.getClass().getResource("/help/uci_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("WEKA format",
        this.getClass().getResource("/help/weka_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("WEKA format",
        this.getClass().getResource("/help/weka_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Excel format",
        this.getClass().getResource("/help/excel_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Excel format",
        this.getClass().getResource("/help/excel_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("DIF format",
        this.getClass().getResource("/help/dif_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("DIF format",
        this.getClass().getResource("/help/dif_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("XML format",
        this.getClass().getResource("/help/xml_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("XML format",
        this.getClass().getResource("/help/xml_format.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("HTML format",
        this.getClass().getResource("/help/html_format.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("HTML format",
        this.getClass().getResource("/help/html_format.html")));
         */
        //Data preparation
        categ = new DefaultMutableTreeNode("Data preparation");
        top.add(categ);
        hoja = new DefaultMutableTreeNode(new HelpSheet("New",
                this.getClass().getResource("/help/data_new.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("New",
                this.getClass().getResource("/help/data_new.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("View",
                this.getClass().getResource("/help/data_view.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("View",
                this.getClass().getResource("/help/data_view.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Data Import",
                this.getClass().getResource("/help/data_import.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Data Import",
                this.getClass().getResource("/help/data_import.html")));

        hoja = new DefaultMutableTreeNode(new HelpSheet("Data Export",
                this.getClass().getResource("/help/data_export.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Data Export",
                this.getClass().getResource("/help/data_export.html")));

        hoja = new DefaultMutableTreeNode(new HelpSheet("Partition",
                this.getClass().getResource("/help/data_partition.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Partition",
                this.getClass().getResource("/help/data_partition.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Edit",
                this.getClass().getResource("/help/data_edit.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Edit",
                this.getClass().getResource("/help/data_edit.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Preparation",
                this.getClass().getResource("/help/data_trans.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Preparation",
                this.getClass().getResource("/help/data_trans.html")));
        /* hoja = new DefaultMutableTreeNode(new HelpSheet("Download",
        this.getClass().getResource("/help/data_download.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Download",
        this.getClass().getResource("/help/data_download.html")));*/

        // Experiment design
        categ = new DefaultMutableTreeNode("Experiments design");
        top.add(categ);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Introduction",
                this.getClass().getResource("/help/exp_intro.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Introduction",
                this.getClass().getResource("/help/exp_intro.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Menu Bar",
                this.getClass().getResource("/help/exp_menu.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Menu Bar",
                this.getClass().getResource("/help/exp_menu.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Tools bar",
                this.getClass().getResource("/help/exp_tool.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Tools bar",
                this.getClass().getResource("/help/exp_tool.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Status bar",
                this.getClass().getResource("/help/exp_status.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Status bar",
                this.getClass().getResource("/help/exp_status.html")));

        // --> Experiment graph
        categ2 = new DefaultMutableTreeNode("Experiment Graph");
        categ.add(categ2);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Datasets",
                this.getClass().getResource("/help/exp_datasets.html")));
        categ2.add(hoja);
        themes.add(new HelpSheet("Datasets",
                this.getClass().getResource("/help/exp_datasets.html")));

        // --> Algorithms
        categ3 = new DefaultMutableTreeNode("Algorithms");
        categ2.add(categ3);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Types",
                this.getClass().getResource("/help/exp_algotypes.html")));
        categ3.add(hoja);
        themes.add(new HelpSheet("Types",
                this.getClass().getResource("/help/exp_algotypes.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Insert algorithm",
                this.getClass().getResource("/help/exp_algoins.html")));
        categ3.add(hoja);
        themes.add(new HelpSheet("Insert algorithm",
                this.getClass().getResource("/help/exp_algoins.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Parameters configuration",
                this.getClass().getResource("/help/exp_algopar.html")));
        categ3.add(hoja);
        themes.add(new HelpSheet("Parameters configuration",
                this.getClass().getResource("/help/exp_algopar.html")));

        /* hoja = new DefaultMutableTreeNode(new HelpSheet("User's methods",
        this.getClass().getResource("/help/exp_user.html")));
        categ2.add(hoja);
        themes.add(new HelpSheet("User's methods",
        this.getClass().getResource("/help/exp_user.html")));*/
        hoja = new DefaultMutableTreeNode(new HelpSheet("Connections",
                this.getClass().getResource("/help/exp_conn.html")));
        categ2.add(hoja);
        themes.add(new HelpSheet("Connections",
                this.getClass().getResource("/help/exp_conn.html")));

        hoja = new DefaultMutableTreeNode(new HelpSheet("Generate experiment",
                this.getClass().getResource("/help/exp_gen.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Generate experiment",
                this.getClass().getResource("/help/exp_gen.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Interface management",
                this.getClass().getResource("/help/exp_inter.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Interface management",
                this.getClass().getResource("/help/exp_inter.html")));

        // RunKeel
        categ = new DefaultMutableTreeNode("RunKEEL");
        top.add(categ);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Executing RunKEEL",
                this.getClass().getResource("/help/run_exe.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Executing RunKEEL",
                this.getClass().getResource("/help/run_exe.html")));
        /*hoja = new DefaultMutableTreeNode(new HelpSheet("Errors during execution",
        this.getClass().getResource("/help/run_err.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Errors during execution",
        this.getClass().getResource("/help/run_err.html")));*/
        hoja = new DefaultMutableTreeNode(new HelpSheet("View results",
                this.getClass().getResource("/help/run_res.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("View results",
                this.getClass().getResource("/help/run_res.html")));

        //Keel docente
        categ = new DefaultMutableTreeNode("Teaching");
        top.add(categ);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Introduction",
                this.getClass().getResource("/help/teach/exp_intro.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Introduction",
                this.getClass().getResource("/help/teach/exp_intro.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Menu Bar",
                this.getClass().getResource("/help/teach/exp_menu.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Menu Bar",
                this.getClass().getResource("/help/teach/exp_menu.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Tools bar",
                this.getClass().getResource("/help/teach/exp_tool.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Tools bar",
                this.getClass().getResource("/help/teach/exp_tool.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Status bar",
                this.getClass().getResource("/help/teach/exp_status.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Status bar",
                this.getClass().getResource("/help/teach/exp_status.html")));

        // --> Experiment graph
        categ2 = new DefaultMutableTreeNode("Experiment Graph");
        categ.add(categ2);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Datasets",
                this.getClass().getResource("/help/teach/exp_datasets.html")));
        categ2.add(hoja);
        themes.add(new HelpSheet("Datasets",
                this.getClass().getResource("/help/teach/exp_datasets.html")));

        // --> Algorithms
        categ3 = new DefaultMutableTreeNode("Algorithms");
        categ2.add(categ3);
        hoja = new DefaultMutableTreeNode(new HelpSheet("Types",
                this.getClass().getResource("/help/teach/exp_algotypes.html")));
        categ3.add(hoja);
        themes.add(new HelpSheet("Types",
                this.getClass().getResource("/help/teach/exp_algotypes.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Insert algorithm",
                this.getClass().getResource("/help/teach/exp_algoins.html")));
        categ3.add(hoja);
        themes.add(new HelpSheet("Insert algorithm",
                this.getClass().getResource("/help/teach/exp_algoins.html")));
        hoja = new DefaultMutableTreeNode(new HelpSheet("Parameters configuration",
                this.getClass().getResource("/help/teach/exp_algopar.html")));
        categ3.add(hoja);
        themes.add(new HelpSheet("Parameters configuration",
                this.getClass().getResource("/help/teach/exp_algopar.html")));

        /* hoja = new DefaultMutableTreeNode(new HelpSheet("User's methods",
        this.getClass().getResource("/help/exp_user.html")));
        categ2.add(hoja);
        themes.add(new HelpSheet("User's methods",
        this.getClass().getResource("/help/exp_user.html")));*/
        hoja = new DefaultMutableTreeNode(new HelpSheet("Connections",
                this.getClass().getResource("/help/teach/exp_conn.html")));
        categ2.add(hoja);
        themes.add(new HelpSheet("Connections",
                this.getClass().getResource("/help/teach/exp_conn.html")));

        hoja = new DefaultMutableTreeNode(new HelpSheet("Interface management",
                this.getClass().getResource("/help/teach/exp_inter.html")));
        categ.add(hoja);
        themes.add(new HelpSheet("Interface management",
                this.getClass().getResource("/help/teach/exp_inter.html")));


    }
}
