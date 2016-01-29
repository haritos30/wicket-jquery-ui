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
package com.googlecode.wicket.jquery.ui.form.dropdown;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.JQueryEvent;
import com.googlecode.wicket.jquery.core.ajax.IJQueryAjaxAware;
import com.googlecode.wicket.jquery.core.ajax.JQueryAjaxBehavior;
import com.googlecode.wicket.jquery.core.event.ISelectionChangedListener;
import com.googlecode.wicket.jquery.ui.JQueryUIBehavior;
import com.googlecode.wicket.jquery.ui.ajax.OnChangeAjaxBehavior;
import com.googlecode.wicket.jquery.ui.ajax.OnChangeAjaxBehavior.ChangeEvent;

/**
 * Provides a JQuery UI Selectmenu widget (DropDownChoice).<br/>
 * This ajax version will post the component, using a {@link OnChangeAjaxBehavior}, when the 'change' javascript method is called.
 *
 * @author Patrick Davids - Patrick1701
 *
 * @param <T> the model object type
 */
public class AjaxDropDownChoice<T> extends DropDownChoice<T> implements ISelectionChangedListener
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public AjaxDropDownChoice(String id)
	{
		super(id);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param choices the list of choices
	 */
	public AjaxDropDownChoice(String id, List<? extends T> choices)
	{
		super(id, choices);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param choices the list of choices
	 * @param renderer the rendering engine
	 */
	public AjaxDropDownChoice(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, choices, renderer);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 * @param choices the list of choices
	 */
	public AjaxDropDownChoice(String id, IModel<T> model, List<? extends T> choices)
	{
		super(id, model, choices);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 * @param choices the list of choices
	 * @param renderer the rendering engine
	 */
	public AjaxDropDownChoice(String id, IModel<T> model, List<? extends T> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, model, choices, renderer);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param choices the list of choices
	 */
	public AjaxDropDownChoice(String id, IModel<? extends List<? extends T>> choices)
	{
		super(id, choices);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 * @param choices the list of choices
	 */
	public AjaxDropDownChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices)
	{
		super(id, model, choices);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param choices the list of choices
	 * @param renderer the rendering engine
	 */
	public AjaxDropDownChoice(String id, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, choices, renderer);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 * @param choices the list of choices
	 * @param renderer the rendering engine
	 */
	public AjaxDropDownChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, model, choices, renderer);
	}

	// Events //

	@Override
	public void onSelectionChanged(AjaxRequestTarget target)
	{
		// noop
	}

	// IJQueryWidget //

	@Override
	public JQueryBehavior newWidgetBehavior(String selector)
	{
		return new AjaxDropDownChoiceBehavior(selector, new ISelectionChangedListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSelectionChanged(AjaxRequestTarget target)
			{
				AjaxDropDownChoice.this.onSelectionChanged(); // updates the model
				AjaxDropDownChoice.this.onSelectionChanged(target);
			}
		});
	}

	/**
	 * Provides a JQuery UI Selectmenu (DropDownChoice) {@link JQueryBehavior}
	 */
	protected static class AjaxDropDownChoiceBehavior extends JQueryUIBehavior implements IJQueryAjaxAware
	{
		private static final long serialVersionUID = 1L;

		private final ISelectionChangedListener listener;
		private JQueryAjaxBehavior onChangeAjaxBehavior;

		public AjaxDropDownChoiceBehavior(String selector, ISelectionChangedListener listener)
		{
			this(selector, DropDownChoice.METHOD, listener);
		}

		public AjaxDropDownChoiceBehavior(String selector, String method, ISelectionChangedListener listener)
		{
			super(selector, method);

			this.listener = Args.notNull(listener, "listener");
		}

		@Override
		public void bind(Component component)
		{
			super.bind(component);

			this.onChangeAjaxBehavior = new OnChangeAjaxBehavior(this, (FormComponent<?>) component);
			component.add(this.onChangeAjaxBehavior);
		}

		@Override
		public void onConfigure(Component component)
		{
			super.onConfigure(component);

			this.setOption("change", this.onChangeAjaxBehavior.getCallbackFunction());
		}

		@Override
		public void onAjax(AjaxRequestTarget target, JQueryEvent event)
		{
			if (event instanceof ChangeEvent)
			{
				this.listener.onSelectionChanged(target);
			}
		}
	}
}
