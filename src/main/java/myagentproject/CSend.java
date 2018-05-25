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
import org.lightjason.agentspeak.action.IBaseAction;
import org.lightjason.agentspeak.agent.IAgent;
import org.lightjason.agentspeak.common.CPath;
import org.lightjason.agentspeak.common.IPath;
import org.lightjason.agentspeak.language.CLiteral;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.ITerm;
import org.lightjason.agentspeak.language.execution.IContext;
import org.lightjason.agentspeak.language.fuzzy.CFuzzyValue;
import org.lightjason.agentspeak.language.fuzzy.IFuzzyValue;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.CTrigger;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.ITrigger;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Based on/credits to https://lightjason.github.io/tutorials/communication/
 */
final class CSend extends IBaseAction
{
    
    private static final long serialVersionUID = 2043960703654019947L;
    
    private final Map<String, MyAgent> m_agents = new ConcurrentHashMap<>();
    @Nonnull
    @Override
    public final IPath name()
    {
        return CPath.from( "message/send" );
    }
    @Override
    public final int minimalArgumentNumber()
    {
        return 2;
    }
    @Override
    public final IFuzzyValue<Boolean> execute( final boolean p_parallel, @Nonnull final IContext p_context,
                                               @Nonnull final List<ITerm> p_argument, @Nonnull final List<ITerm> p_return )
    {
        
        final MyAgent l_receiver = m_agents.get( p_argument.get( 0 ).<String>raw() );
        
        if ( l_receiver == null )
            return CFuzzyValue.from( false );
        
        l_receiver.trigger(
            CTrigger.from(
                ITrigger.EType.ADDGOAL,
                
                
                CLiteral.from(
                    "message/receive",
                    
                    CLiteral.from(
                        "message",
                        
                        
                        p_argument
                            .subList( 1, p_argument.size() )
                            .stream()
                            .map( i -> CRawTerm.from( i.raw() ) )
                    ),
                    
                    
                    
                    CLiteral.from(
                        "from",
                        CRawTerm.from(
                                p_context.agent().<MyAgent>raw().getname()
                        )
                    )
                )
            )
        );
        return CFuzzyValue.from( true );
    }

    @Nonnull
    final MyAgent register( @Nonnull final MyAgent p_agent )
    {
        m_agents.put( p_agent.getname(), p_agent );
        return p_agent;
    }

    @Nonnull
    final MyAgent unregister( @Nonnull final MyAgent p_agent )
    {
        m_agents.remove( p_agent.getname() );
        return p_agent;
    }
    
  
}