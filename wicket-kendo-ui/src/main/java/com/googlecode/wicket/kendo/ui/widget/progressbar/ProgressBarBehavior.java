/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.kendo.ui.widget.progressbar;

import org.apache.wicket.Component;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;

/**
 * Provides a Kendo UI progress-bar behavior.
 *
 * @author Sebastien Briquet - sebfz1
 * @since 6.17.0
 */
public class ProgressBarBehavior extends JQueryBehavior
{
	private static final long serialVersionUID = 1L;

	static final String METHOD = "kendoProgressBar";

	/**
	 * Constructor
	 *
	 * @param selector the html selector (ie: "#myId")
	 */
	public ProgressBarBehavior(String selector)
	{
		super(selector, METHOD);
	}

	/**
	 * Constructor
	 *
	 * @param selector the html selector (ie: "#myId")
	 * @param options the {@link Options}
	 */
	public ProgressBarBehavior(String selector, Options options)
	{
		super(selector, METHOD, options);
	}

	// Methods //

//	@Override
//	public void bind(Component component)
//	{
//		super.bind(component);
//
//		component.add(this.onChangeBehavior = this.newOnChangeBehavior());
//	}

	// Events //

	@Override
	public void onConfigure(Component component)
	{
		super.onConfigure(component);

		this.setOption("value", component.getDefaultModelObjectAsString()); // initial value
		//this.setOption("change", this.onChangeBehavior.getCallbackFunction());
	}

	// Factories //
//
//	/**
//	 * Gets a new {@link JQueryAjaxPostBehavior} that will be called on 'change' javascript event
//	 *
//	 * @return the {@link JQueryAjaxBehavior}
//	 */
//	protected JQueryAjaxPostBehavior newOnChangeBehavior()
//	{
//		return new JQueryAjaxChangeBehavior(this);
//	}
}
