// package com.ebook.book.security;



// import java.io.IOException;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.ebook.book.response.CustomException;
// import com.ebook.book.response.CustomResponseEntity;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.GenericFilterBean;
// import org.springframework.web.filter.OncePerRequestFilter;

// public class JwtTokenFilter extends OncePerRequestFilter {

//   private JwtTokenProvider jwtTokenProvider;

//   public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
//     this.jwtTokenProvider = jwtTokenProvider;
//   }

//   @Override
//   protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//     String token = jwtTokenProvider.resolveToken(httpServletRequest);
//     try {
//       if (token != null && jwtTokenProvider.validateToken(token)) {
//         Authentication auth = jwtTokenProvider.getAuthentication(token);
//         SecurityContextHolder.getContext().setAuthentication(auth);
//       }
//     } catch (CustomException ex) {
//       SecurityContextHolder.clearContext();
//       CustomResponseEntity exception = CustomResponseEntity.builder().code(ex.getHttpStatusCode()).status(ex.getHttpStatusCode())
//       .message(ex.getMessage()).data("").build();

      
//     }

//     filterChain.doFilter(httpServletRequest, httpServletResponse);
//   }

// }