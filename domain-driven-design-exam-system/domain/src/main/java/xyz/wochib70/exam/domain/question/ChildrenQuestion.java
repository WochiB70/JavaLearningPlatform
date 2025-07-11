package xyz.wochib70.exam.domain.question;

import xyz.wochib70.exam.domain.IdentifierId;

public interface ChildrenQuestion {


    IdentifierId identifierId();

    IdentifierId getParentId();
}
