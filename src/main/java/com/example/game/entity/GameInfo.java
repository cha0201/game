package com.example.game.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "game_info")
@Getter
@Setter
public class GameInfo {

        @Id
        private ObjectId id;
        @Field("goalbf_id")
        private String goalbfId;
        @Field("game_union_name")
        private String gameUnionName;
        @Field("game_day_time")
        private String gameDayTime;
        @Field("game_finished_status")
        private String gameFinishedStatus;
        @Field("game_team_a")
        private String gameTeamA;
        @Field("game_score")
        private String gameScore;
        @Field("game_team_b")
        private String gameTeamB;
        @Field("game_half_time_socre")
        private String gameHalfTimeSocre;
        @Field("game_union_date")
        private String gameUnionDate;
        @Field("pankou_data")
        private List<org.bson.Document> pankouData;

}
