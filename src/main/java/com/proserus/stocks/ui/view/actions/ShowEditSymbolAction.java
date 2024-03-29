package com.proserus.stocks.ui.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import com.proserus.stocks.bo.symbols.Symbol;
import com.proserus.stocks.bp.events.Event;
import com.proserus.stocks.bp.events.EventBus;
import com.proserus.stocks.bp.events.EventListener;
import com.proserus.stocks.bp.events.ModelChangeEvents;
import com.proserus.stocks.ui.view.symbols.SymbolsModificationView;

public class ShowEditSymbolAction extends AbstractAction implements EventListener {
	public static int keyEvent = KeyEvent.VK_E;
	private static final long serialVersionUID = 201404031810L;
	private Symbol selectedSymbol = null;
	private static ShowEditSymbolAction singleton = new ShowEditSymbolAction();

	private ShowEditSymbolAction() {
		EventBus.getInstance().add(this, ModelChangeEvents.SYMBOL_SELECTION_CHANGED);
		setEnabled(false);
	}

	public static ShowEditSymbolAction getInstance() {
		return singleton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new SymbolsModificationView(selectedSymbol).setVisibile(true);
	}

	@Override
	public void update(Event event, Object model) {
		if (ModelChangeEvents.SYMBOL_SELECTION_CHANGED.equals(event)) {
			selectedSymbol = ModelChangeEvents.SYMBOL_SELECTION_CHANGED.resolveModel(model);
			setEnabled(selectedSymbol != null);
		}
	}
}
