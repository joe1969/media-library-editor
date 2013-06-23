package de.ravenfly.mle.gui.episode;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import de.ravenfly.mle.gui.data.DataAction;
import de.ravenfly.mle.modulebase.DataObserver;

public class ReloadAction extends DataAction<EpisodeContext> implements DataObserver {

	private static final long serialVersionUID = 2301416988097542239L;

	public ReloadAction(EpisodeContext context) {
		super(context);

		putValue(NAME, "Reopen");
		putValue(SHORT_DESCRIPTION, "Reopen the file");
		putValue(SMALL_ICON, new ImageIcon(ReloadAction.class.getResource("/rescources/icons/small/arrow_refresh.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);

		setEnabled(false);
		context.addDataObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(context != null && context.isModified()){
			context.load();
		}
	}

	@Override
	public void done() {
		setEnabled(context.isLoaded() && context.isModified());
	}
}
