package xyz.wochib70.exam.domain.question.repository;

import xyz.wochib70.exam.domain.question.MaterialCapableQuestion;

public interface ModifyMaterialRepository {

    MaterialCapableQuestion findByIdentifier(String s);

    void save(MaterialCapableQuestion question);
}
