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

import sun.misc.Unsafe;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * main application with runtime
 * Based on/credits to https://lightjason.org/tutorials/agentspeak-in-fifteen-minutes/
 * and Based on/credits to https://lightjason.github.io/tutorials/communication/
 */
final class App
{
    private CopyOnWriteArrayList<MyAgent> m_agent = new CopyOnWriteArrayList<>();
    static
    {
        // disable logger
        LogManager.getLogManager().reset();
    }

    /**
     * private constructor to avoid any instantiation
     */
    private App()
    {
    }


    /**
     * main method
     *
     * @param p_args command-line arguments
     */
    public static void main( final String[] p_args )
    {
        disableWarning();
        if ( p_args.length < 2 )
            throw new RuntimeException( "arguments are not set: ASL script, number of cycles" );

        // parameter of the command-line arguments:
        // 1. ASL file for The Bose agent
        // 2. ASL file for one Dilbert agent
        // 3. number of iterations (if not set maximum integer)
        Set<MyAgent> l_agents = new HashSet<>();
        final CSend m_send = new CSend();
        try
        {
            final FileInputStream l_initiatorstream = new FileInputStream( p_args[0] );
            String l_initiatorname = p_args[0].toString().replace(".asl", "");

            if ( p_args.length > 2 )
            {
                final FileInputStream l_followerstream = new FileInputStream( p_args[1] );
                String l_respondername = p_args[1].toString().replace(".asl", "");

                l_agents.add( new MyAgentGenerator( l_followerstream, m_send, l_respondername )
                        .generatesingle( 1 ) );
            }

            l_agents.add( new MyAgentGenerator( l_initiatorstream, m_send, l_initiatorname )
                    .generatesingle( 1 ) );

        }
        catch ( final Exception l_exception )
        {
            l_exception.printStackTrace();

            return;
        }

        // runtime call (with parallel execution)

        IntStream
            .range(
                0,
                p_args.length < 3
                ? Integer.parseInt( p_args[1] )
                : Integer.parseInt( p_args[2] )
            )
            .forEach( j -> l_agents.parallelStream()
                                   .forEach( i ->
                                   {
                                       try
                                       {
                                           i.call();
                                       }
                                       catch ( final Exception l_exception )
                                       {
                                           l_exception.printStackTrace();
                                           throw new RuntimeException();
                                       }
                                   } ) );
    }

    private static void disableWarning()
    {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }
}
