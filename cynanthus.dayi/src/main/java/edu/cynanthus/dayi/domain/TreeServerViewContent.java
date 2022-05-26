package edu.cynanthus.dayi.domain;

import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;
import java.util.Objects;

public class TreeServerViewContent<N extends RuntimeNode> extends ServerViewContent {

    private final List<GeneralNode<N>> generalNodes;

    public TreeServerViewContent(ServerInfo serverInfo, Boolean available, List<GeneralNode<N>> generalNodes) {
        super(serverInfo, available);
        this.generalNodes = generalNodes;
    }

    public List<GeneralNode<N>> getGeneralNodes() {
        return generalNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TreeServerViewContent<?> that = (TreeServerViewContent<?>) o;
        return Objects.equals(generalNodes, that.generalNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), generalNodes);
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "generalNodes:" + generalNodes +
            '}';
    }

}
