package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgvEquipInfo {
    private String floor;
    private String agvId;
    private String podId;
    private String load;
    private String x;
    private String y;
    private String z;
    private String rotation;

    public String toString() {
        return "{" +
                "floor:" +  floor + "," +
                "id:" + agvId + "," +
                "podId:" + podId + "," +
                "load:" + load + "," +
                "currentPosition: {"+
                "x:" + x + "," +
                "y:" + y + "," +
                "z:" + z + "," +
                "rotation:" +  rotation +
                "}" +
                "}";
    }
}
