package ru.fsep.enterprise.fseper.controllers.converters;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fsep.enterprise.fseper.controllers.dto.PersonInfoDto;
import ru.fsep.enterprise.fseper.models.PersonInfo;
import ru.fsep.enterprise.fseper.test.data.*;


import static org.junit.Assert.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataAPI.initPersonInfoDto;
import static ru.fsep.enterprise.fseper.test.data.TestDataCore.initPersonInfo;

/**
 * Author Fedorov Juriy on 03.09.2015
 */
public class UserConverterImplTest {
    private PersonInfo personInfo = initPersonInfo(); ;

    @Autowired
    private UserConverter userConverter = new UserConverterImpl();

    @Before
    public void setUp() throws Exception {
        personInfo = initPersonInfo();
    }

    @Test
    public void testFromPersonInfo() throws Exception {
        PersonInfoDto expected = initPersonInfoDto();
        System.out.println(expected);
        PersonInfoDto actual = userConverter.fromPersonInfo(personInfo);
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}