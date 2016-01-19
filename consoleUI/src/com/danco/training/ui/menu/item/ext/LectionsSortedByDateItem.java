package com.danco.training.ui.menu.item.ext;

import java.util.List;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionsSortedByDateItem extends MenuItem {

	public LectionsSortedByDateItem(Menu menu) {
		super("Get lections sorted by date item.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			List<Lection> lections = facade.getLectionsSortedByDate();
			if (lections == null){
				entityDisplayer.displayMessage("Lections not found.");
			}else if (lections.size()==0){
				entityDisplayer.displayMessage("Lections not found.");
			}
			else{
				entityDisplayer.displayLections(lections, "Lections sorted by date");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

	

}
