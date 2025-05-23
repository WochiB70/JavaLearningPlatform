package xyz.wochib70.exam.domain.support;

import org.springframework.util.Assert;
import xyz.wochib70.exam.common.AbstratcAggregate;
import xyz.wochib70.exam.common.IdentifierId;
import xyz.wochib70.exam.domain.CalculateScoreType;
import xyz.wochib70.exam.domain.QuestionAggregate;
import xyz.wochib70.exam.domain.RenderType;
import xyz.wochib70.exam.domain.ShelvesStatus;

public abstract class AbstractQuestionAggregate extends AbstratcAggregate implements QuestionAggregate {

    private String question;

    private CalculateScoreType calculateScoreType;

    private RenderType renderType;

    private ShelvesStatus shelvesStatus;


    public AbstractQuestionAggregate(IdentifierId identifierId, RenderType renderType) {
        super(identifierId);
        Assert.notNull(renderType, "renderType must not be null");
        this.shelvesStatus = ShelvesStatus.SHELVES_OFF;
        this.renderType = renderType;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public void updateQuestion(String question) {
        canModify();
        this.question = question;
    }

    @Override
    public CalculateScoreType getScoreType() {
        return calculateScoreType;
    }

    @Override
    public void updateScoreType(CalculateScoreType scoreType) {
        canModify();
        this.calculateScoreType = scoreType;
    }

    @Override
    public void updateRenderType(RenderType renderType) {
        canModify();
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
        this.validate();
        this.shelvesStatus = ShelvesStatus.SHELVES_ON;
    }

    @Override
    public void shelvesOff() {
        this.shelvesStatus = ShelvesStatus.SHELVES_OFF;
    }

    protected boolean canModify() {
        return this.shelvesStatus == ShelvesStatus.SHELVES_OFF;
    }

    protected void checkModify() {
        if (!this.canModify()) {
            throw new UnsupportedOperationException("当前问题不能被修改");
        }
    }

    protected abstract void validate();
}
