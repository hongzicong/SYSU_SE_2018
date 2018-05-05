package solution;

import java.util.ArrayList;
import java.util.Queue;
import java.util.List;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {

        Queue<JigsawNode> exploreList;  // 用以保存已发现但未访问的节点
        Set<JigsawNode> visitedList;    // 用以保存已发现的节点

        exploreList.addElement(bNode);
        currentJNode = bNode;
        while(!exploreList.isEmpty()){
            if(currentJNode.equals(eNode)){
                return true;
            } else {
                visitedList.addElement(currentJNode);
                List<JigsawNode> adjacentNodes = getAdjJNodes(exploreList, visitedList, currentJNode);
                for(JigsawNode temp : adjacentNodes){
                    exploreList.addElement(temp);
                }
                exploreList.remove(0);
                currentJNode = exploreList.firstElement();
                visitedList.addElement(currentJNode);
            }
        }

        return false;
    }

    
    private List<JigsawNode> getAdjJNodes(Queue<JigsawNode> exploreList, Set<JigsawNode> visitedList, JigsawNode jigsawNode){
        ArrayList<JigsawNode> resultJNodes = new ArrayList<>();
        for(int i = 0; i < 4; ++i){
            JigsawNode temp = new JigsawNode(jigsawNode);
            temp.move(i);
            if(!visitedList.contains(temp) && !exploreList.contains(temp)){
                resultJNodes.add(temp);
            }
        }
        return resultJNodes;
    }

    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        jNode.setEstimatedValue(s);
    }
}
