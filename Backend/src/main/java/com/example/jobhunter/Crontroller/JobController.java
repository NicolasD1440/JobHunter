    package com.example.jobhunter.Crontroller;

    import com.example.jobhunter.DTO.CreateJobDTO;
    import com.example.jobhunter.DTO.JobMatchDTO;
    import com.example.jobhunter.DTO.JobSearchRequest;
    import com.example.jobhunter.DTO.MatchFilterDTO;
    import com.example.jobhunter.ExceptionError.ErrorResponse;
    import com.example.jobhunter.ExceptionError.ScraperException;
    import com.example.jobhunter.Model.Job;
    import com.example.jobhunter.Model.RawJob;
    import com.example.jobhunter.Service.JobService;
    import jakarta.validation.Valid;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;


    @RestController
    @RequestMapping("api/jobs") //URL Base
    public class JobController {
        private JobService service;


        public JobController(JobService service){
            this.service = service;
        }


        @GetMapping
          public List<Job> getJobs(){
            return service.getJobs();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Job> getJobById(@PathVariable Integer id){
            Job job = service.getOneJob(id);
            return ResponseEntity.ok(job);
        }

        @PostMapping
          public ResponseEntity<Job> createJob(@Valid @RequestBody CreateJobDTO create){
            Job jobs = service.saveJobs(create);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(jobs);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Job> updateJob(@PathVariable Integer id, @Valid @RequestBody CreateJobDTO create){
                Job job = service.UpdateJob(id, create);
                return ResponseEntity.ok(job);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteJob(@PathVariable Integer id){
                service.deleteJob(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .build();
        }

        @PostMapping("/import")
        public ResponseEntity<Job> importJobs(@RequestBody RawJob rawJob){
         Job job = service.saveRawJob(rawJob);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(job);

        }


        @PostMapping("/matches/{id}")
        public ResponseEntity<List<JobMatchDTO>> getFilterJobs(@PathVariable Integer id, @RequestBody MatchFilterDTO filter){
            List<JobMatchDTO> dto = service.getMatchingJobs(id, filter);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(dto);
        }

        //ENDPOINT SCRAPER "PARAMETROS DE BUSQUEDA"
        @PostMapping("/search")
        public  ResponseEntity<List<RawJob>> searchJobs(@RequestBody JobSearchRequest request){
            List<RawJob> response = service.searchJobs(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        @PostMapping("/search/jobs")
        public  ResponseEntity<List<Job>> searchAndNormalize(@RequestBody JobSearchRequest request){
            List<Job> response = service.searchAndNormalize(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        //ENDPOINT DE PRUEBA
        @GetMapping("/testError")
        public ResponseEntity<ErrorResponse> testError(){
            throw new IllegalArgumentException("mensaje secreto");
        }
    }
