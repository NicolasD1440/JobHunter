package com.example.jobhunter.Service;

import com.example.jobhunter.DTO.CreateJobDTO;
import com.example.jobhunter.DTO.JobMatchDTO;
import com.example.jobhunter.DTO.JobSearchRequest;
import com.example.jobhunter.DTO.MatchFilterDTO;
import com.example.jobhunter.ExceptionError.JobNotFoundException;
import com.example.jobhunter.Filter.JobFilter;
import com.example.jobhunter.Matcher.JobMatcher;
import com.example.jobhunter.Model.Job;
import com.example.jobhunter.Model.Profile;
import com.example.jobhunter.Model.RawJob;
import com.example.jobhunter.Repository.JobRepository;
import com.example.jobhunter.Repository.ProfileRepository;
import com.example.jobhunter.ScraperService.ElEmpleoScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private static final Logger log = LoggerFactory.getLogger(JobService.class);
    private JobRepository repository;
    private JobNormallizer jobNormallizer;
    private ProfileService serviceProfile;
    private ElEmpleoScraperService scraper;

    public JobService(JobRepository repository, JobNormallizer jobNormallizer, ProfileService serviceProfile, ElEmpleoScraperService scraper){
        this.repository = repository;
        this.jobNormallizer = jobNormallizer;
        this.serviceProfile = serviceProfile;
        this.scraper = scraper;
    }
    //OBTENER TODOS LOS EMPLEOS
    public List<Job> getJobs(){
        return repository.findAll();
    }
    // BUSCAR UN EMPLEO POR ID
    public Job getOneJob(Integer id){
        Optional<Job> job = repository.findById(id);
        return job.orElseThrow(() -> new JobNotFoundException("Job no encontrado"));
    }
    //GUARDAR UN EMPLEO
    public Job saveJobs(CreateJobDTO create) {
        // 1. Extraer los datos del DTO
        String title = create.getTitle();
        String company = create.getCompany();
        String location = create.getLocation();

        // Condiciones
        Integer salaryMin = create.getSalaryMin();
        Integer salaryMax = create.getSalaryMax();
        String workMode = create.getWorkMode();
        String contractType = create.getContractType();

        // Requisitos
        List<String> requiredEducation = create.getRequiredEducation();
        Integer requiredYearsExperience = create.getRequiredYearsExperience();
        List<String> requiredSkills = create.getRequiredSkills();

        // Información adicional y Origen
        String description = create.getDescription();
        String source = create.getSource();
        String sourceUrl = create.getSourceUrl();

        // 2. Instanciar la entidad con los nuevos atributos
        Job job = new Job(
                title,
                company,
                location,
                salaryMin,
                salaryMax,
                workMode,
                contractType,
                requiredEducation,
                requiredYearsExperience,
                requiredSkills,
                description,
                source,
                sourceUrl
        );

        // 3. Guardar en el repositorio
        return repository.save(job);
    }
    //ACTUALIZAR UN EMPLEO
    public Job UpdateJob(Integer id, CreateJobDTO create) {
        Job job = getOneJob(id);

            // Información general
            job.setTitle(create.getTitle());
            job.setCompany(create.getCompany());
            job.setLocation(create.getLocation());

            // Condiciones
            job.setSalaryMin(create.getSalaryMin());
            job.setSalaryMax(create.getSalaryMax());
            job.setWorkMode(create.getWorkMode());
            job.setContractType(create.getContractType());

            // Requisitos
            job.setRequiredEducation(create.getRequiredEducation());
            job.setRequiredYearsExperience(create.getRequiredYearsExperience());
            job.setRequiredSkills(create.getRequiredSkills());

            // Información adicional
            job.setDescription(create.getDescription());

            // Origen
            job.setSource(create.getSource());
            job.setSourceUrl(create.getSourceUrl());

            return repository.save(job);
    }
     //ELIMINAR UN EMPLEO
     public void deleteJob(Integer id){
        Job job = getOneJob(id);
        repository.delete(job);
    }
    // GUARDAR UN EMPLEO - DESDE EL SCRAPER
    public Job saveRawJob(RawJob rawJob){
        Job job = jobNormallizer.normalize(rawJob);
        return repository.save(job);

    }
    //OBTENER COINCIDENCIA ENTRE PERFIL Y EMPLEO
        public List<JobMatchDTO> getMatchingJobs(Integer profileId, MatchFilterDTO filter){
            List<Job> jobs = repository.findAll();
            JobMatcher matcher = new JobMatcher();
            List<JobMatchDTO> listDTO = new ArrayList<>();
            Profile profile= serviceProfile.getOneProfile(profileId);
            Double minScore = filter.getMinScore();
            JobFilter jobFilter = new JobFilter();
            if(minScore == null){
             minScore = 0.0;
            }
            for (int i = 0; i < jobs.size(); i++) {
                Integer id = jobs.get(i).getId();
                String title = jobs.get(i).getTitle();
                String company = jobs.get(i).getCompany();
                String location = jobs.get(i).getLocation();
                Double matchScore= matcher.calculateMatch(profile,jobs.get(i));
                if (matchScore >= minScore && jobFilter.isJobValid(filter, jobs.get(i))){
                    JobMatchDTO jobMatcher = new JobMatchDTO(id, title, company, location, matchScore);
                    listDTO.add(jobMatcher);
                }
            }
            listDTO.sort(
                    Comparator.comparing(JobMatchDTO::getMatchScore).reversed()
            );

            return listDTO;
    }
    //PARAMETROS DE BUSQUEDA DEL SCRAPER
    public List<RawJob> searchJobs(JobSearchRequest request){
        ElEmpleoScraperService scraper = new ElEmpleoScraperService();
        return scraper.search(request);
    }
    //CONVERTIR UNA LISTA DE RAWJOBS EN JOBS
    public List<Job> searchAndNormalize(JobSearchRequest request) {

        List<RawJob> rawJobs = scraper.search(request);
        List<Job> normalizedJobs = new ArrayList<>();
        for (int i = 0; i < rawJobs.size(); i++) {
            normalizedJobs.add(jobNormallizer.normalize(rawJobs.get(i)));
        }

        repository.saveAll(normalizedJobs);
        return normalizedJobs;
    }


}
