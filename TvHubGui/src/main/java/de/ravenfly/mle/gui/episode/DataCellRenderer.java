package de.ravenfly.mle.gui.episode;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import de.ravenfly.mle.modulebase.DataContext;

class DataCellRenderer<T> extends JLabel implements ListCellRenderer {
 
	private static final long serialVersionUID = 1055645232200587939L;

	public DataCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

    	@SuppressWarnings("unchecked")
		DataContext<T> context =(DataContext<T>) value;

    	setText(context.getBaseName());
		setBorder(new EmptyBorder(2, 5, 2, 5));


        Color background;
        Color foreground;

        if (isSelected) {
        	background = list.getSelectionBackground();
        	foreground = list.getSelectionForeground();
        } else {
        	//background = list.getBackground();
        	background = Color.WHITE;
            foreground = list.getForeground();
        };

        setBackground(background);
        setForeground(foreground);

        return this;
    }
}