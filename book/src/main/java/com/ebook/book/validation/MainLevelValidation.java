package com.ebook.book.validation;

import javax.validation.GroupSequence;

@GroupSequence(value = { LevelOneValidation.class, LevelTwoValidation.class })
public interface MainLevelValidation {
    
}
