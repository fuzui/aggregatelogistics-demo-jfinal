package net.fuzui.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kdks.model.ExpressParam;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExpressSingleParam extends ExpressParam {
	private static final long serialVersionUID = 1L;

	/**
	 * 	快递号
	 */
	private String expressNo;

}
