package com.damidiaz;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.damidiaz.model.Categoria;
import com.damidiaz.model.Perfil;
import com.damidiaz.model.Usuario;
import com.damidiaz.model.Vacante;
import com.damidiaz.repository.CategoriasRepository;
import com.damidiaz.repository.PerfilesRepository;
import com.damidiaz.repository.UsuariosRepository;
import com.damidiaz.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	
	//prueba git
	
	
	@Autowired
	private CategoriasRepository repoCategorias;
	@Autowired
	private VacantesRepository repoVacantes;
	@Autowired
	private UsuariosRepository repoUsuarios;
	@Autowired
	private PerfilesRepository repoPerfiles;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		//guardar();
		//buscarPorId();
		//modificar();
		//eliminar();
		//conteo();
		//this.encontrarPorIds();
		//this.buscarTodos();
		//this.existeId();
		//this.guardarTodos();
		//this.buscarTodosJpa();
		//this.borrarTodoEnBloque();
		//this.buscarTodosOrdenados();
		//this.buscarTodosPaginacion();
		//this.buscarTodosPorPaginacionOrdenados();
		//this.buscarVacantes();
		//this.guardarVacante();
		//this.crearPerfilesAplicacion();
		//this.crearUsuariosConDosPerfil();
		//this.buscarUsuario();
		//this.buscarVacantesPorEstatus();
		//this.buscarVacantesPorDestacadoEstatus();
		//this.buscarVacanteSalario();
		this.buscarVacantesVariosEstatus();
	}
	
	private void buscarVacantesPorEstatus() {
		List<Vacante> lista =this.repoVacantes.findByEstatus("APROBADA");
		System.out.println("Registros encontrados:" + lista.size());
		for(Vacante v : lista) {
			
			System.out.println(v.getId()+" "+v.getNombre()+" "+v.getEstatus());
			
		}
	}
	
	private void buscarVacantesPorDestacadoEstatus() {
		List<Vacante> lista =this.repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1,"APROBADA");
		System.out.println("Registros encontrados:" + lista.size());
		for(Vacante v : lista) {
			
			System.out.println(v.getId()+" "+v.getNombre()+" "+v.getEstatus()+" "+v.getDestacado());
			
		}
	}
	
	private void buscarVacanteSalario() {
		List<Vacante> lista =this.repoVacantes.findBySalarioBetweenOrderBySalarioDesc(7000,14000);
		System.out.println("Registros encontrados:" + lista.size());
		for(Vacante v : lista) {
			
			System.out.println(v.getId()+" "+v.getNombre()+" "+v.getEstatus()+" $"+v.getSalario());
			
		}
	}
	
	private void buscarVacantesVariosEstatus() {
		String []estatus = new String[] {"Eliminada","Creada"};
		
		List<Vacante> lista =this.repoVacantes.findByEstatusIn(estatus);
		System.out.println("Registros encontrados:" + lista.size());
		for(Vacante v : lista) {
			
			System.out.println(v.getId()+" "+v.getNombre()+" "+v.getEstatus());
			
		}
		
		
	}
	
	private void crearUsuariosConDosPerfil() {
		Usuario user = new Usuario();
		user.setNombre("Damian Diaz");
		user.setEmail("damidiaz.2209@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("damidiaz");
		user.setPassword("dami2209");
		user.setEstatus(1);
		
		Perfil perfil1 = new Perfil();
		perfil1.setId(2);
		Perfil perfil2 = new Perfil();
		perfil2.setId(3);
	
		user.agregar(perfil1);
		user.agregar(perfil2);
		
		repoUsuarios.save(user);
		
	}
	
	private void buscarUsuario() {
		Optional<Usuario> opcional = this.repoUsuarios.findById(1);
		
		if(opcional.isPresent()) {
			Usuario user = opcional.get();
			System.out.println("Usuario:"+ user.getNombre());
			System.out.println("Perfiles asignados:");
			
			for(Perfil p : user.getPerfiles()) {
				System.out.println(p.getPerfil());
			}
		}else {
			System.out.println("Usuario no encontrado");
		}
		
		
	}
	
	private List<Perfil> getPerfilesAplicacion(){
		List<Perfil> perfiles = new LinkedList<Perfil>();
		Perfil perfil = null;
		
		perfil = new Perfil();
		perfil.setPerfil("SUPERVISOR");
		perfiles.add(perfil);
		
		perfil = new Perfil();
		perfil.setPerfil("ADMINISTRADOR");
		perfiles.add(perfil);
		
		perfil = new Perfil();
		perfil.setPerfil("USUARIO");
		perfiles.add(perfil);
		
		return perfiles;
	}
	
	private void crearPerfilesAplicacion() {
		this.repoPerfiles.saveAll(this.getPerfilesAplicacion());
	}
	
	private void buscarVacantes() {
		List<Vacante> lista =repoVacantes.findAll();
		for(Vacante v : lista) {
			System.out.println(v.getId() +" "+ v.getNombre()+"->"+v.getCategoria().getNombre());
		}
	}
	
	
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de matematicas");
		vacante.setDescripcion("Escuela primaria solicita profesor de matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.00);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("imagen.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de matematicas</h1>");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);
		this.repoVacantes.save(vacante);
		
		
	}
	
	
	// borrar todo los registro con una sola sentencia delete
	private void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
	}
	
	// metodo para buscar todas las categorias y ordenarlas por un atributo(nombre) y ordenarlos de manera descendente
	private void buscarTodosOrdenados() {
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());
		for(Categoria c : categorias) {
			
			System.out.println(c.getId() +" "+c.getNombre());
			
		}
		
	}
	
	// metodo buscar todos pero con paginacion
	private void buscarTodosPaginacion() {
		Page<Categoria> page=repoCategorias.findAll(PageRequest.of(1,5));
		System.out.println("Total registros:" + page.getTotalElements());
		System.out.println("Total de paginas:" + page.getTotalPages());
		for(Categoria c : page.getContent()) {
			System.out.println(c.getId()+""+c.getNombre());
		}
				
	}
	
	// metodo buscar todos pero con paginacion y ordenados
	private void buscarTodosPorPaginacionOrdenados() {	
		Page<Categoria> page=repoCategorias.findAll(PageRequest.of(0,5,Sort.by("nombre").descending()));
		System.out.println("Total registros:" + page.getTotalElements());
		System.out.println("Total de paginas:" + page.getTotalPages());
		for(Categoria c : page.getContent()) {
			System.out.println(c.getId()+""+c.getNombre());
		}

	}
	
	private void guardar() {
		
		Categoria categoria = new Categoria();
		categoria.setNombre("Sistemas");
		categoria.setDescripcion("sistemas de la informacion");
		repoCategorias.save(categoria);
		System.out.println(categoria);
	
	
	}
	
	private List<Categoria> getListaCategorias(){
		List<Categoria> categorias = new LinkedList<Categoria>();
		Categoria categoria = null;
		
		categoria = new Categoria();
		categoria.setNombre("Programador de blockchain");
		categoria.setDescripcion("Trabajo relacionado con criptomonedas");
		categorias.add(categoria);
		
		categoria = new Categoria();
		categoria.setNombre("Soldador/Pintura");
		categoria.setDescripcion("Trabajo relacionado soldadura / pintura");
		categorias.add(categoria);
	
		categoria = new Categoria();
		categoria.setNombre("Ingeniero industrial");
		categoria.setDescripcion("Trabajo relacionado con ingenieria industrial");
		categorias.add(categoria);
		
		
		return categorias;
	}
	
	private void buscarTodosJpa() {
		List<Categoria> categorias = repoCategorias.findAll();
		for(Categoria c : categorias) {
			
			System.out.println(c.getId() +" "+c.getNombre());
			
		}
	}
	
	private void guardarTodos() {
		
		List<Categoria> categorias = this.getListaCategorias();
		
		repoCategorias.saveAll(categorias);
		
	}
	
	private void buscarTodos() {
		
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for(Categoria c : categorias) {
			System.out.println("La categoria existe;"+ c);
		}
		
	}
	
	private void existeId() {
		
		boolean existe = repoCategorias.existsById(50);
		
		System.out.println(existe);
	}
	
	private void conteo() {
		
		long count = repoCategorias.count();
		System.out.println("Cantidad de categorias:" + count);
	}
	
	
	private void buscarPorId() {
		Optional<Categoria> optional = repoCategorias.findById(3);
		if(optional.isPresent()) {
			System.out.println(optional.get());
		}else {
			System.out.println("categoria no encontrada");
		}
	}
	
	private void eliminar() {
		int idCategoria = 3;
		repoCategorias.deleteById(idCategoria);
	}
	
	private void eliminarTodos() {
		repoCategorias.deleteAll();
	}
	
	private void encontrarPorIds() {
		
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		
		Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
		
		for(Categoria c : categorias) {
			System.out.println(c);
		}
	}
	
	private void modificar() {
		
		Optional<Categoria> optional = repoCategorias.findById(5);
		if(optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ingenieria de softwere");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repoCategorias.save(catTmp);
			System.out.println(optional.get());
		}else {
			System.out.println("categoria no encontrada");
		}
			
	}
	
	
}
