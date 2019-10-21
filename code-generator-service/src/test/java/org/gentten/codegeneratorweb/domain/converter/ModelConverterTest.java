package org.gentten.codegeneratorweb.domain.converter;

import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.codegeneratorweb.domain.form.edit.ModelEditForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author : duanzhiqiang
 * @date : 2019-10-21 10:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class ModelConverterTest {
    @Autowired
    ModelConverter modelConverter;

    @Test
    public void form2Entity() {

        final ModelEditForm editForm = ModelEditForm.builder()
                .comment("转化测试")
                .name("test")
                .moduleName("test")
                .packageName("org.gentten")
                .build();

        final Model model = modelConverter.form2Entity(editForm);

        assertEquals(editForm.getComment(), model.getComment());
    }
}