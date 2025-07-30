package com.example.demo.DTO;

import java.util.List;
import java.util.Map;

public class ReactionListResponseDTO {
    private Map<String, Integer> counts;
    private List<ReactionResponseDTO> reactions;

    public ReactionListResponseDTO(Map<String, Integer> counts, List<ReactionResponseDTO> reactions) {
        this.counts = counts;
        this.reactions = reactions;
    }

    //getter setter
    public Map<String, Integer> getCounts(){
        return this.counts;
    }
    public void setCounts(Map<String, Integer> counts){
        this.counts = counts;
    }

    public List<ReactionResponseDTO> getReactions(){
        return this.reactions;
    }
    public void setReactions(List<ReactionResponseDTO> reactions){
        this.reactions = reactions;
    }
}
