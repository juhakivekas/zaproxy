package org.zaproxy.zap.view.table.decorator;

import org.jdesktop.swingx.decorator.AbstractHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;

import org.parosproxy.paros.extension.history.ExtensionHistory;
import org.parosproxy.paros.model.HistoryReference;

import java.awt.*;

public class MarkItemColorHighlighter extends AbstractHighlighter {
    private int idColumnIndex;
    private ExtensionHistory extHistory;
    public MarkItemColorHighlighter(ExtensionHistory extHistory, int idColumnIndex){
        super();
        setHighlightPredicate(new MarkItemColorHighlighterPredicate());
        this.extHistory = extHistory;
        this.idColumnIndex = idColumnIndex;
    }

    @Override
    protected Component doHighlight(Component component, ComponentAdapter adapter) {
        HistoryReference ref = extHistory.getHistoryReference((int) adapter.getValue(idColumnIndex));
        try{
            int color = ref.getHttpMessage().getColor();
            if(color != 0){
               component.setBackground(new Color(color));
            }
        }catch(Exception e){}
        return component;
    }

    private class MarkItemColorHighlighterPredicate implements HighlightPredicate{

        @Override
        public boolean isHighlighted(Component component, ComponentAdapter adapter) {
            return true;
        }
    }
}
