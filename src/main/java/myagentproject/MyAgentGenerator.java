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

import org.lightjason.agentspeak.common.CCommon;
import org.lightjason.agentspeak.generator.IBaseAgentGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * agent generator to create agents
 * Based on/credits to https://lightjason.org/tutorials/agentspeak-in-fifteen-minutes/
 * and Based on/credits to https://lightjason.github.io/tutorials/communication/
 */
final class MyAgentGenerator extends IBaseAgentGenerator<MyAgent>
{
    private final String m_name;
    private final CSend m_send;
    /**
     * @param p_stream ASL code as any stream e.g. FileInputStream
     */
    MyAgentGenerator( @Nonnull final InputStream p_stream, @Nonnull final CSend p_send, @Nonnull final String p_name ) throws Exception
    {
        super(
            // input ASL stream
            p_stream,

            // we use all build-in actions of LightJason
            Stream.concat(
                CCommon.actionsFromPackage(),
                Stream.of( p_send )
            ).collect( Collectors.toSet() )
        );
        m_name = p_name;
        m_send = p_send;
    }

    /**
     * generator method of the agent
     *
     * @param p_data any data which can be put from outside to the generator method
     * @return returns an agent
     */
    @Nullable
    @Override
    public final MyAgent generatesingle( @Nullable final Object... p_data )
    {
        return m_send.register(
                new MyAgent( m_configuration, m_name )
        );
    }

    final void unregister( final MyAgent p_agent )
    {
        m_send.unregister( p_agent );
    }
}
