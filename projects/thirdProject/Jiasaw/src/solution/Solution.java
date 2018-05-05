package solution;

import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;

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
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode){
        try{
            String filePath = "BFSearchDialog.txt";  
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));  
    
            beginJNode = bNode;
            endJNode = eNode;
            boolean isFound = false;
    
            Set<JigsawNode> visitedList = new HashSet<>(1000);
            Queue<JigsawNode> exploreList = new PriorityQueue<>(500, new Comparator<JigsawNode>() {
                @Override
                public int compare(JigsawNode a, JigsawNode b) {
                    if (a.getEstimatedValue() < b.getEstimatedValue()) {
                        return -1;
                    } else if (a.getEstimatedValue() > b.getEstimatedValue()) {
                        return 1;
                    } else if (a.getNodeDepth() < b.getNodeDepth()) {
                        return -1;
                    } else if (a.getNodeDepth() > b.getNodeDepth()) {
                        return 1;
                    }
                    return 0;
                }
            });
    
            exploreList.add(bNode);
            currentJNode = bNode;
            while(!exploreList.isEmpty()){
                if(currentJNode.equals(eNode)){
                    isFound = true;
                    getPath();
                    break;
                } else {
                    visitedList.add(currentJNode);
                    List<JigsawNode> adjacentNodes = getAdjJNodes(exploreList, visitedList, currentJNode);
                    for(JigsawNode temp : adjacentNodes){
                        exploreList.add(temp);
                    }
                    currentJNode = exploreList.poll();
                    visitedList.add(currentJNode);
                }
            }
    
            this.printResult(pw);  
            pw.close();
    
            return isFound;
        }catch(IOException e){
            e.printStackTrace();
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
        int d = 0;
        int distance = 0;
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if(jNode.getNodesState()[index] == 0){
            	continue;
            }
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
            if (jNode.getNodesState()[index] != index) {
                d++;
                int num = jNode.getNodesState()[index];
                int dim = JigsawNode.getDimension();
                int x1 = (num - 1) % dim;
                int y1 = (num - 1) / dim;
                int x2 = (index - 1) % dim;
                int y2 = (index - 1) / dim;
                distance += Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }
        }

        jNode.setEstimatedValue(s + d + distance);
    }
}
