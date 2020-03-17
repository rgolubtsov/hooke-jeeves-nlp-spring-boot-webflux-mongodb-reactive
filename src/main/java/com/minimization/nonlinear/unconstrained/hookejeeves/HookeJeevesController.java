/*
 * src/main/java/com/minimization/nonlinear/unconstrained/hookejeeves/
 * HookeJeevesController.java
 * ============================================================================
 * The Hooke and Jeeves nonlinear unconstrained minimization algorithm.
 * Microservice. Version 0.1
 * ============================================================================
 * Written by Radislav (Radicchio) Golubtsov, 2020
 *
 * (See the LICENSE file at the top of the source tree.)
 */

package com.minimization.nonlinear.unconstrained.hookejeeves;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HookeJeevesController {
    @PutMapping("store")
    public void store_initial_guess_data(
        @RequestParam(name="nvars",    defaultValue="2"   ) final String nvars,
        @RequestParam(name="startpt0", defaultValue="-1.2") final String startpt0,
        @RequestParam(name="startpt1", defaultValue="1.0" ) final String startpt1,
        @RequestParam(name="rho",      defaultValue="0.5" ) final String rho) {

        System.out.println("==> nvars="    + nvars    + "\n"
                         + "==> startpt0=" + startpt0 + "\n"
                         + "==> startpt1=" + startpt1 + "\n"
                         + "==> rho="      + rho);
    }

    @GetMapping("solve")
    public void solve_the_problem_get(
        @RequestParam(name="obj_func",
              defaultValue="f-of-x") final String obj_func) {

        System.out.println("==> obj_func=" + obj_func);
    }

    @PostMapping("solve")
    public void solve_the_problem_post(
        @RequestParam(name="obj_func",
              defaultValue="f-of-x") final String obj_func) {

        solve_the_problem_get(obj_func);
    }
}

// vim:set nu et ts=4 sw=4:
