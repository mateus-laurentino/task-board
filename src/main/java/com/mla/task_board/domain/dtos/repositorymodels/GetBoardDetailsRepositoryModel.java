package com.mla.task_board.domain.dtos.repositorymodels;

public class GetBoardDetailsRepositoryModel {
    private Integer id;
    private String name;
    private Integer position;
    private Integer cardId;
    private String cardTitle;
    private String cardDescription;
    private String cardState;
    private Boolean cardBlock;
    private String backgroundColor;
    private String fontColor;
    private String backgroundColorCard;
    private String fontColorCard;

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getCardId() {
        return cardId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getCardState() {
        return cardState;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getFontColor(){
        return fontColor;
    }

    public String getBackgroundColorCard() {
        return backgroundColorCard;
    }

    public String getFontColorCard(){
        return fontColorCard;
    }

    public Boolean getCardBlock() {
        return cardBlock;
    }

    public Boolean notEmptyCards(){
        return cardId != null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public void setCardState(String cardState) {
        this.cardState = cardState;
    }

    public void setCardBlock(Boolean cardBlock) {
        this.cardBlock = cardBlock;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFontColor(String fontColor){
        this.fontColor = fontColor;
    }

    public void setBackgroundColorCard(String backgroundColorCard) {
        this.backgroundColorCard = backgroundColorCard;
    }

    public void setFontColorCard(String fontColorCard){
        this.fontColorCard = fontColorCard;
    }
}
