package com.owm.lottery.model.event;

import com.owm.lottery.model.apiplus.Graph;
import com.owm.lottery.model.event.common.IEvent;

/**
 * 划图操作
 * Created by ouweiming on 2017/4/5.
 */

public class GraphHandleEvent implements IEvent {
    /**  添加划图 操作  */
    private static final int TYPE_ADD = 0x1;
    /**  添加完成划图 操作  */
    private static final int TYPE_ADDED = 0x2;
    
    /**  操作类型  */
    private int mOperationType;
    /**  划图数据  */
    private Graph mGraph;

    private GraphHandleEvent(int operationType, Graph graph) {
        mOperationType = operationType;
        mGraph = graph;
    }

    public static GraphHandleEvent create(int operationType, Graph graph) {
        return new GraphHandleEvent(operationType, graph);
    }

    public int getOperationType() {
        return mOperationType;
    }

    public void setOperationType(int operationType) {
        this.mOperationType = operationType;
    }

    public Graph getGraph() {
        return mGraph;
    }

    public void setGraph(Graph graph) {
        mGraph = graph;
    }
}
