/*
 * @cond LICENSE
 * ######################################################################################
 * # LGPL License                                                                       #
 * #                                                                                    #
 * # This file is part of the LightJason                                                #
 * # Copyright (c) 2015-16, LightJason (info@lightjason.org)                            #
 * # This program is free software: you can redistribute it and/or modify               #
 * # it under the terms of the GNU Lesser General Public License as                     #
 * # published by the Free Software Foundation, either version 3 of the                 #
 * # License, or (at your option) any later version.                                    #
 * #                                                                                    #
 * # This program is distributed in the hope that it will be useful,                    #
 * # but WITHOUT ANY WARRANTY; without even the implied warranty of                     #
 * # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                      #
 * # GNU Lesser General Public License for more details.                                #
 * #                                                                                    #
 * # You should have received a copy of the GNU Lesser General Public License           #
 * # along with this program. If not, see http://www.gnu.org/licenses/                  #
 * ######################################################################################
 * @endcond
 */

package myagentproject;

import org.lightjason.agentspeak.agent.IBaseAgent;
import org.lightjason.agentspeak.configuration.IAgentConfiguration;

import javax.annotation.Nonnull;


/**
 * agent class with annotation to mark the class that actions are inside
 * Based on/credits to https://lightjason.org/tutorials/agentspeak-in-fifteen-minutes/
 * and Based on/credits to https://lightjason.github.io/tutorials/communication/
 */
final class MyAgent extends IBaseAgent<MyAgent>
{

    /**
     * serial id
     */
    private static final long serialVersionUID = -2111543876806742109L;
    private String m_name;

    /**
     * constructor of the agent
     *
     * @param p_configuration agent configuration of the agent generator
     */
    MyAgent( @Nonnull final IAgentConfiguration<MyAgent> p_configuration, final String p_name )
    {
        super( p_configuration );
        m_name = p_name;
    }

    public String getname() { return m_name; }

    void setname(final String p_name)
    {
        this.m_name = p_name;
    }

}
