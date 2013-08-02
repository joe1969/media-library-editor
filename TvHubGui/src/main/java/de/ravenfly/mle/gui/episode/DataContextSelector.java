package de.ravenfly.mle.gui.episode;

import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.ravenfly.mle.gui.open.OpenObserver;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.ContextObserver;

public class DataContextSelector<T> extends JPanel implements OpenObserver<T>{

	private final static int SIZE = 150;

	private static final long serialVersionUID = 5114235926423026397L;

	private JList list;
	private DataContext<T> oldContext;
	private List<ContextObserver<T>> observers; 

	public DataContextSelector() {

		observers = new ArrayList<ContextObserver<T>>();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{300, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new DataCellRenderer<T>());

		list.addListSelectionListener(new ListSelectionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()){
					DataContext<T> context = (DataContext<T>) list.getSelectedValue();

					if(oldContext != null && oldContext.isModified()){
						//System.out.println("NO! Old Context Reason: (Null = " + (oldContext == null) + ", Modified = " + oldContext.isModified() +")");
					} else {

						if(oldContext != null){
							oldContext.close();
						}

						for (ContextObserver<T> observer : observers) {
							observer.setContext(context);
						}
						context.open();
						oldContext = context;
					}
				}
			}
		});

		scrollPane.setViewportView(list);

		setMinimumSize(new Dimension(SIZE, SIZE));
		setPreferredSize(new Dimension(SIZE, SIZE));
	}

	public void addContextObserver(ContextObserver<T> observer){
		observers.add(observer);
	}

	public void removeContextObserver(ContextObserver<T> observer){
		if(observers.contains(observer)){
			observers.remove(observer);
		}
	}

	@Override
	public void contextOpen(final List<DataContext<T>> contextList) {
		list.setModel(new AbstractListModel() {

			private static final long serialVersionUID = -7683864095459710628L;

			@Override
			public int getSize() {
				return contextList.size();
			}

			@Override
			public Object getElementAt(int i) {
				return contextList.get(i);
			}
		});
	}
}
