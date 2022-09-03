package com.nus.cool.core.cohort.refactor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nus.cool.core.iceberg.query.Aggregation;
import com.nus.cool.core.iceberg.query.SelectionQuery;
import java.io.IOException;
import java.util.List;
import lombok.Getter;
import java.io.File;



@Getter
public class OlapQueryLayout {

  // inner structure
  public enum granularityType{
    DAY,
    MONTH,
    YEAR,
    NULL
  }

  // datasource name
  @JsonProperty("dataSource")
  private String dataSource;

  // select condition
  @JsonProperty("selection")
  private SelectionQuery selection;

  // a list a groupFields
  @JsonProperty("groupFields")
  private List<String> groupFields;

  // a list of aggregation functions, sum, count etc
  @JsonProperty("aggregations")
  private List<Aggregation> aggregations;

  // selected time range
  @JsonProperty("timeRange")
  private String timeRange;

  // granularity for time range
  @JsonProperty("granularity")
  private granularityType granularity;

  // granularity for groupBy, if the groupBy field is dataType,
  @JsonProperty("groupFields_granularity")
  private granularityType groupFields_granularity;

  public static OlapQueryLayout readFromJson(File inputFile) throws IOException{
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(inputFile, OlapQueryLayout.class);
  }

  public static OlapQueryLayout readFromJson(String path) throws IOException{
    return readFromJson(new File(path));
  }

}
