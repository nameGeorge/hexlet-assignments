package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    TagInterface inputTag;
    String label;

    public LabelTag(String label, TagInterface inputTag) {
        this.inputTag = inputTag;
        this.label = label;
    }

    public String render(){
        return "<label>"
                + label
                + inputTag.render()
                + "</label>";
    }
}
// END
