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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HookeJeevesController {
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
