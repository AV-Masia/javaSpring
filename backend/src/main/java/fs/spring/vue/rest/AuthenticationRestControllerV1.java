package fs.spring.vue.rest;

//@RestController
//@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

//    @PostMapping("/login")
//    public String login(@RequestBody AuthenticationRequestDTO request) {
//        return "/developers";
//    }
////    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    public void AuthenticationRestController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request){
//        try {
//            String email = request.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(eemail, request.getPassword()));
////            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist: " + email));
////            String token = jwtTokenProvider.createToken(email, user.getRole().name());
//            Map<Object, Object> response = new HashMap<>();
//            response.put("eemail", eemail);
////            response.put("token", token);
//
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            return  new ResponseEntity<>("Invalid eemail/password combination: " + request.getUsername(), HttpStatus.FORBIDDEN);
//        }
//    }
//
////    @PostMapping("/login")
////    public String login(@RequestBody AuthenticationRequestDTO request){
////        return "/developers";
////    }
//
//    @PostMapping("/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response){
//        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
//        securityContextLogoutHandler.logout(request, response, null);
//    }

}
