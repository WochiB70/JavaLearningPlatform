package xyz.wochib70.exam.domain.question.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;
import xyz.wochib70.exam.domain.AbstratcAggregate;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.CalculateScoreType;
import xyz.wochib70.exam.domain.question.QuestionAggregate;
import xyz.wochib70.exam.domain.question.RenderType;
import xyz.wochib70.exam.domain.question.ShelvesStatus;

@Getter
@Setter
public class DefaultQuestion extends AbstratcAggregate implements QuestionAggregate {

    private String question;

    private CalculateScoreType calculateScoreType;

    private RenderType renderType;

    private ShelvesStatus shelvesStatus;

    public DefaultQuestion(IdentifierId identifierId) {
        super(identifierId);
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public void updateQuestion(String question) {
        this.question = question;
    }

    @Override
    public CalculateScoreType getScoreType() {
        return calculateScoreType;
    }

    @Override
    public void updateScoreType(CalculateScoreType scoreType) {
        this.calculateScoreType = scoreType;
    }

    @Override
    public void updateRenderType(RenderType renderType) {
        Assert.notNull(renderType, "renderType must not be null");
        this.renderType = renderType;
    }

    @Override
    public RenderType getRenderType() {
        return this.renderType;
    }

    @Override
    public void shelvesOn() {
        if (shelvesStatus == ShelvesStatus.SHELVES_ON) {
            throw new IllegalStateException("当前问题已被上架");
        }
        this.shelvesStatus = ShelvesStatus.SHELVES_ON;
    }

    @Override
    public void shelvesOff() {
        if (shelvesStatus == ShelvesStatus.SHELVES_OFF) {
            throw new IllegalStateException("当前问题已被下架");
        }
        this.shelvesStatus = ShelvesStatus.SHELVES_OFF;
    }

}
