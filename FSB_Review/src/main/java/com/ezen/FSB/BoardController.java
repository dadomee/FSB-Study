package com.ezen.FSB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.FSB.dto.AlarmDTO;
import com.ezen.FSB.dto.BoardDTO;
import com.ezen.FSB.dto.BoardFilesDTO;
import com.ezen.FSB.dto.Board_replyDTO;
import com.ezen.FSB.dto.MemberDTO;
import com.ezen.FSB.dto.NoticeDTO;
import com.ezen.FSB.dto.ReportDTO;
import com.ezen.FSB.service.AlarmMapper;
import com.ezen.FSB.service.BoardMapper;
import com.ezen.FSB.service.MemberMapper;
import com.ezen.FSB.service.MypageMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper boardMapper;
	
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MypageMapper myPageMapper;
	
	@Autowired
	AlarmMapper alarmMapper;
	
	@Resource(name = "uploadPath")
	private String upPath;
	
	//mode�� �����Խ���,�͸�Խ���, �߰�Խ��� ��������
	//�����Խ��� mode = ""
	//�͸�Խ��� mode = "anony"
	//�߰�Խ��� ( ���߿� ��Ʈ�ѷ��� �и����� ) mode = "sh"
	
	//�����Խ��� ����Ʈ 
	@RequestMapping("/board_free.do")
	public ModelAndView board_free_list(HttpServletRequest req, java.util.Map<String, Integer> params) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("upPath", session.getServletContext().getRealPath("resources/images"));


		//�������� ����Ʈ
		String mode = req.getParameter("mode");
		//mode�� �Խ��� ���� �ٸ� �������� ������
		
		List<NoticeDTO> nlist =boardMapper.nlistBoard(mode);
		
		//��ȸ�� �� (�����Խ���)
		List<BoardDTO> readlist = boardMapper.readlist();
		mav.addObject("readlist", readlist);
		//��ۼ�
		List<BoardDTO> replylist = boardMapper.replylist();
		mav.addObject("replylist", replylist);
			//������ �ѹ�
		int pageSize = 10; // ���������� �� � ������.
		
		//�Խñ� ��� �ʼ�
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // ���� ������ 
		int startRow = (currentPage - 1) * pageSize + 1; 
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountBoard();
		params.put("start", startRow);
		params.put("end", endRow);

		if (endRow > count)
			endRow = count;
		List<BoardDTO> list = null;
		if (count > 0) {
			list = boardMapper.listBoard(params);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			//���� ���ǹ� true�� 0 false�� 1
			
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.addObject("count", count);
		mav.addObject("nlistBoard", nlist);
		mav.addObject("listBoard", list);
		mav.setViewName("board/list_free");
		return mav;
	}
	

	//�͸�Խ��� ����Ʈ 
	@RequestMapping("/board_anony.do")
	public ModelAndView boardAnony(HttpServletRequest req, java.util.Map<String, Integer> params) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("upPath", session.getServletContext().getRealPath("resources/images"));
		
		//�������� ����Ʈ 
				String mode = req.getParameter("mode");
				List<NoticeDTO> nlist =boardMapper.nlistBoard(mode);
		//��ȸ�� ��
				List<BoardDTO> readlist = boardMapper.readlist_a();
				mav.addObject("readlist", readlist);
		//��ۼ�
				List<BoardDTO> replylist = boardMapper.replylist_a();
				mav.addObject("replylist", replylist);
		//������ �ѹ�
		int pageSize = 10;
 
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountBoard_anony();
		
		params.put("start", startRow);
		params.put("end", endRow);
		params.put("mode", 1);
		
		if (endRow > count)
			endRow = count;
		List<BoardDTO> list = null;

		if (count > 0) {
			list =boardMapper.listBoard(params);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		mav.addObject("count", count);
		mav.addObject("listBoard", list);
		mav.addObject("nlistBoard", nlist);
		mav.setViewName("board/list_anony");
		return mav;
	}
	//�����Խ��� �ۼ�, �͸� �Խ��� �� �ۼ� �� ����
	@RequestMapping(value = "/write_board.do", method = RequestMethod.GET)
	public ModelAndView writeFormBoard(HttpServletRequest req, String mode) {
		int num = 0, re_group = 0, re_step = 0, re_level = 0;
		ModelAndView mav = new ModelAndView();
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		String snum = req.getParameter("board_num");
		
		if (snum != null) { // ���� �Ѿ�� board_num ������ ������ �ƴ϶� �����.
			num = Integer.parseInt(snum);
			re_group = Integer.parseInt(req.getParameter("board_re_group"));
			re_step = Integer.parseInt(req.getParameter("board_re_step"));
			re_level = Integer.parseInt(req.getParameter("board_re_level"));
		}
		
		mav.addObject("board_num", num);
		mav.addObject("mem_num", mdto.getMem_num());
		mav.addObject("board_re_group", re_group);
		mav.addObject("board_re_step", re_step);
		mav.addObject("board_re_level", re_level);
		mav.addObject("mode", mode);
		
		mav.setViewName("board/writeForm");
		return mav;
	}
	//���� �͸�Խ��� ���ۼ� 
		@RequestMapping(value = "/write_board.do", method = RequestMethod.POST)
		public String writeProBoard(HttpServletRequest req, String mode, @RequestParam("filename") List<MultipartFile> multiFileList,@ModelAttribute BoardDTO dto, BindingResult result)
				throws IllegalStateException, IOException {
			if (result.hasErrors()) {
				dto.setBoard_img1("");
				dto.setBoard_img2("");
				dto.setBoard_img3("");
				dto.setBoard_img4("");
			}
			//�α��� üũ 
			HttpSession session= req.getSession();
				MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
				if(mdto == null) {
					session.invalidate();
					req.setAttribute("msg","�α��� ���ּ���" );
					req.setAttribute("url","login.do" );
					return "message";
				}
			if(mode.equals("anony")) { //�͸�Խ��ǿ��� ���� ������ ��� �͸� ����
				dto.setBoard_anony_check(1);
				req.setAttribute("board_anony_check",dto.getBoard_anony_check());
			}
			req.setAttribute("mode", "all");
			dto.setBoard_ip(req.getRemoteAddr()); // ip �޾Ƽ� set
			
			//�̹��� �ޱ�
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req; // multipart�� �����ޱ�
			MultipartFile mf = mr.getFile("board_img1");
			MultipartFile mf2 = mr.getFile("board_img2");
			MultipartFile mf3 = mr.getFile("board_img3");
			MultipartFile mf4 = mr.getFile("board_img4");
			
			String board_img1 = mf.getOriginalFilename(); //���� �޾ƿ� �̸�
			String board_img2 = mf2.getOriginalFilename();
			String board_img3 = mf3.getOriginalFilename();
			String board_img4 = mf4.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID(); //�̹��� �ߺ� �� �����ڽ� ������ ���� ���ϸ�

			String upPath = session.getServletContext().getRealPath("/resources/img"); // �̹����� upPath
			String upPath1 = session.getServletContext().getRealPath("/resources/files"); // ���� ���ε� upPath1
			session.setAttribute("upPath1", upPath1);
			session.setAttribute("upPath", upPath);
			
			MemberDTO dto3 = (MemberDTO)session.getAttribute("login_mem");
			 dto.setMem_num(dto3.getMem_num());
			BoardFilesDTO fdto = new BoardFilesDTO();

			if (!mf.isEmpty()) { // �̹��� ÷�� �Ǿ����� ��
				board_img1 = uuid.toString() + "_" + board_img1;
				File file1 = new File(upPath, board_img1);
				mf.transferTo(file1);
				dto.setBoard_img1(board_img1);
				
			} else if (mf.isEmpty()) { // �̹��� ÷�� �ȵǾ�������
				dto.setBoard_img1("");
			}
				
			 if (!mf2.isEmpty()) {
				board_img2 = uuid.toString() + "_" + board_img2; // ���� ���ϸ� �ٿ��ֱ� 
				File file2 = new File(upPath, board_img2);
				mf2.transferTo(file2); // �̹��� �÷��ֱ�
				dto.setBoard_img2(board_img2);
				
			} else if (mf2.isEmpty()) {
				dto.setBoard_img2("");
			}
			if (!mf3.isEmpty()) {
				board_img3 = uuid.toString() + "_" + board_img3;
				File file3 = new File(upPath, board_img3);
				mf3.transferTo(file3);
				dto.setBoard_img3(board_img3);
			} else if (mf3.isEmpty()) {
				dto.setBoard_img3("");
			}
			
			if (!mf4.isEmpty()) {
				board_img4 = uuid.toString() + "_" + board_img4;
				File file4 = new File(upPath, board_img4);
				mf4.transferTo(file4);
				dto.setBoard_img4(board_img4);
			} else {
				dto.setBoard_img4("");
			}
//			
//			if(dto.getBoard_img1().equals("")){
//				dto.setBoard_img1(board_img2);
//				dto.setBoard_img2(board_img3);
//				dto.setBoard_img3(board_img4);
//			}
			
			req.setAttribute("dto", dto);
			
			int res = boardMapper.insertBoard(dto);
			if (res > 0) {
				  File fileCheck = new File(upPath1); // ���ε� �� ���� check
			         if (!fileCheck.exists()) // ���� ������ ���ٸ�, 
			            fileCheck.mkdirs(); //���� ���丮 ������ֱ�
			         List<Map<String, String>> fileList = new ArrayList<>();
			         if(multiFileList == null || multiFileList.get(0).getOriginalFilename().equals("")) { // writeform���� �������� ��Ƽ� �Ű������� �޾ƿ�.
			        	 // ���� ���� ������ �׳� �Խñ� ���
			        	 if(mode.equals("anony")) {
			 				req.setAttribute("msg", "�Խñ� ��� ����");
			 				req.setAttribute("url", "board_anony.do?mode=anony");
			 			}else {
			 			req.setAttribute("msg", "�Խñ� ��� ����");
			 			req.setAttribute("url", "board_free.do?mode=");
			             return "message";
			 			}
			         }else { // ���� ������ �ִٸ� 
			         for (int i = 0; i < multiFileList.size(); i++) { //for������ ���� �̸� �޾ƿ���, ������ �޾ƿ���
			            String originFile = multiFileList.get(i).getOriginalFilename();
			            int filesize = (int) multiFileList.get(i).getSize();
			            String ext = originFile.substring(originFile.lastIndexOf(".")); //�����̸� Ȯ���� �տ��� �ڸ��� 
			            String changeFile = UUID.randomUUID().toString() + ext;// �����̸��� ���ο� �����̸� �ٿ��ֱ�
			            fdto.setFilename(originFile); //���� �����̸�
			            fdto.setSavename(changeFile);//db�� ����� ���ο� �����̸� 
			            fdto.setFilesize(filesize);
			            int board_num = boardMapper.maxRe_group(); // �ֽű۹�ȣ �������� (��� ���ó����  ��)
			          	fdto.setBoard_num(board_num);
			            int res2 = boardMapper.fileInsert(fdto);
			            Map<String, String> map = new HashMap<>();
			            map.put("filename", originFile);
			            map.put("savename", changeFile);
			            fileList.add(map);
			         }
			        }
			         // ���� ���ε�
			         try {
			        	 if (fileList.size()!=0) {
			            for (int i = 0; i < multiFileList.size(); i++) {
			               File uploadFile = new File(upPath1 + "\\" + fileList.get(i).get("savename"));
			               multiFileList.get(i).transferTo(uploadFile); //i��° ���� ���������� ���ε� ���ֱ�.
			            }
			        	 }
			            System.out.println("���� ���� ���ε� ����");
			         } catch (IllegalStateException | IOException e) {
			            System.out.println("�������� ���ε� ���� �Ф�");
			            for (int i = 0; i < multiFileList.size(); i++) {
			               new File(upPath1 + "\\" + fileList.get(i).get("savename")).delete();
			               //������ ������� ����� �����ֱ�
			            }
			            e.printStackTrace();
			         }
				if(mode.equals("anony")) {
					req.setAttribute("msg", "�Խñ� ��� ����");
					req.setAttribute("url", "board_anony.do?mode=anony");
				}else {
				req.setAttribute("msg", "�Խñ� ��� ����");
				req.setAttribute("url", "board_free.do?mode=");
				}
			}else {
				req.setAttribute("msg", "�Խñ� ��� ����");
				req.setAttribute("url", "write_board.do");
			}
			return "message";
		}
		
		
	//���� �ٿ�ε� ó��
	@RequestMapping("/file_download.do")
	public ModelAndView fileDown(HttpServletRequest req,HttpServletResponse resp,@RequestParam int board_num) {
		ModelAndView mav = new ModelAndView("message"); 
		List<BoardFilesDTO> list = boardMapper.getFiles(board_num);
		 
		HttpSession session=req.getSession();
		//�α��� üũ 
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) { // �α��� �Ǿ��ִ��� üũ
				session.invalidate(); 
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		String upPath1 = session.getServletContext().getRealPath("/resources/files");
			session.setAttribute("upPath1", upPath1);
			req.setAttribute("upPath1", upPath1);
			
			String name = req.getParameter("filename");
			 try {
		            String original_name = null;
		            String save_name = null;
		      
		            for(BoardFilesDTO fdto : list) { // ���� �̸��� ������ �ִ��� Ȯ��
		               if(fdto.getFilename().equals(name)){
		                  original_name = fdto.getFilename();
		                  save_name = fdto.getSavename();
		               }
		            }
		            //���� �ٿ�ε� �κ��� ���� ���� �� �����ϰ� �ּ� �ްԿ� .. 
		            original_name = new String(original_name.getBytes("UTF-8"), "iso-8859-1"); 
		            File file = new File(session.getServletContext().getRealPath("/resources/files/"+save_name));
		            FileInputStream fis = new FileInputStream(file); // ���� �б� 
		               ServletOutputStream sos = resp.getOutputStream();
		               
		               resp.setContentType("application/octet-stream");
		               resp.setContentLength((int) file.length());
		               resp.setHeader("Content-Disposition", "attachment;filename=\""+ original_name +"\"");
		         
		               byte[] buffer = new byte[4096]; 
		               int bytesRead;
		               while ((bytesRead = fis.read(buffer)) != -1) {
		                   sos.write(buffer, 0, bytesRead);
		               }
		               fis.close();
		               sos.close();
		         } catch (IOException e) {
		            e.printStackTrace();
		         }
			 return mav;
	}
	//�������� �󼼺���
	
	@RequestMapping("/board_noti_content.do") 
	public ModelAndView boardNotiContent(HttpServletRequest req,@RequestParam int n_num,@RequestParam String mode) {
		ModelAndView mav = new ModelAndView("/board/content_noti");
		NoticeDTO dto = boardMapper.getNotice(n_num);
		if(mode==null) {
			mode = "";
		}
		mav.addObject("mode",mode);
		mav.addObject("getNotice",dto);
		return mav;
	}
	
	//����, �͸� �Խñ� �󼼺���
	@RequestMapping("/content_board.do")
	public ModelAndView getBoard(HttpServletRequest req, @RequestParam int board_num, @RequestParam int pageNum, @RequestParam(required = false) Map<String,Integer> params) throws IOException {

		HttpSession session = req.getSession();
		ModelAndView mav = new ModelAndView("/board/content");
		//�α��� üũ 
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		String upPath = session.getServletContext().getRealPath("/resources/img");
		BoardDTO dto = boardMapper.getBoard(board_num,"content");
		mav.addObject("getBoard", dto);
		
		String mode = req.getParameter("mode");
		mav.addObject("mode",mode);
		//���ϸ���Ʈ �̱�
		 List<BoardFilesDTO> flist = boardMapper.getFiles(board_num);
		 mav.addObject("listFile", flist);
		
		// ������� �¹̰� ���� (�Ű����� int board_num -> Map<String, Integer> params �� �ٲ�
		// ��� ��� ������ �� 
		 int pageSize = 10;
		if (pageNum == 0) {
			pageNum = 1;
		}
		int currentPage = pageNum;
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountReply(board_num);
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("start", startRow);
		map2.put("end", endRow);
		map2.put("board_num", board_num);
		
		params.put("board_num",board_num);
		params.put("board_replycount",count);
		int board_replycount= boardMapper.setReply(params);

		if (endRow > count)
			endRow = count;
		List<Board_replyDTO> list = null;
		if (count > 0) {
			list = boardMapper.listReply(map2);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
			mav.addObject("pageNum",currentPage);
			mav.addObject("board_replycount",board_replycount);
		}
		mav.addObject("count", count);
		mav.addObject("listReply", list);
		return mav;
	
	}
	//�Խ˱�, ��� �Ű� â ����
	//���� �� �����ּҿ��� ����,�͸�,�߰� �Խ����� �Խñ� ����� �Ű�â�� �� ������ϴ�.
	@RequestMapping("/report_board.do")
		public ModelAndView reportBoard(HttpServletRequest req,String mode) {
			ModelAndView mav = new ModelAndView("message");
				if(mode.equals("board")) {
						mav.addObject("board_num",Integer.parseInt(req.getParameter("board_num")));
				}else if(mode.equals("reply")){
						mav.addObject("br_num",Integer.parseInt(req.getParameter("br_num")));
				}else if(mode.equals("sh_board")) {
						mav.addObject("board_num",Integer.parseInt(req.getParameter("board_num")));
				}else {
						mav.addObject("br_num",Integer.parseInt(req.getParameter("br_num")));
				}
					mav.addObject("mode",mode);
					mav.setViewName("board/reportForm");
						return mav;
	}
	//�͸�, ���� �Խñ� , ��� �Ű� �ѱ��
	@RequestMapping(value="/report_board.do", method = RequestMethod.POST)
	public ModelAndView reportBoardPro(HttpServletRequest req,String mode, @ModelAttribute ReportDTO dto, BindingResult result) {
		ModelAndView mav = new ModelAndView("closeWindow");
		HttpSession session = req.getSession();

		MemberDTO dto3 = (MemberDTO)session.getAttribute("login_mem");
		dto.setMem_num(dto3.getMem_num());
		dto.setReport_content(Integer.parseInt(req.getParameter("board_report")));
		
		if(mode.equals("board")) {
			int board_num = Integer.parseInt(req.getParameter("board_num"));
			dto.setReport_target(board_num);
			dto.setReport_mode("����,�͸�Խñ�");
			boardMapper.report(dto);
			int res = boardMapper.reportBoard(board_num);
				if (res > 0) {
					mav.addObject("msg", "�Ű�Ǿ����ϴ�.");
				} else {
					mav.addObject("msg", "�Ű� ����, �����ڿ��� ������ �ּ���");
				}
				
		}else if(mode.equals("sh_board")) {
			int board_num = Integer.parseInt(req.getParameter("board_num"));
			dto.setReport_mode("�߰�Խñ�");
			dto.setReport_target(board_num);
			boardMapper.report(dto);
			int res = boardMapper.reportBoard_sh(board_num);
			if (res > 0) {
				mav.addObject("msg", "�Ű�Ǿ����ϴ�.");
			} else {
				mav.addObject("msg", "�Ű� ����, �����ڿ��� ������ �ּ���");
			}
			
		}else if(mode.equals("sh_reply")) {
			int br_num = Integer.parseInt(req.getParameter("br_num"));
			dto.setReport_mode("�߰���");
			dto.setReport_target(br_num);
			boardMapper.report(dto);
			int res = boardMapper.reportReply_sh(br_num);
			if (res > 0) {
				mav.addObject("msg", "�Ű�Ǿ����ϴ�.");
			} else {
				mav.addObject("msg", "�Ű� ����, �����ڿ��� ������ �ּ���");
			}
			
		}else if(mode.equals("reply")) {
			int br_num = Integer.parseInt(req.getParameter("br_num"));
			dto.setReport_mode("����,�͸���");
			dto.setReport_target(br_num);
			boardMapper.report(dto);
			int res = boardMapper.reportReply(br_num);
				if (res > 0) {
					mav.addObject("msg", "�Ű�Ǿ����ϴ�.");
				} else {
					mav.addObject("msg", "�Ű� ����, �����ڿ��� ������ �ּ���");
				}
		}
		return mav;
	}

	//��� ����
		@RequestMapping("/delete_reply.do")
		public ModelAndView deleteReply(HttpServletRequest req, int board_num,int br_num, int pageNum) {
			ModelAndView mav = new ModelAndView("message");
			//�α��� üũ 
			HttpSession session= req.getSession();
				MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
				if(mdto == null) {
					session.invalidate();
					mav.setViewName("message_back");
					mav.addObject("msg","�α��� ���ּ���" );
					return mav;
				}
			int res = boardMapper.deleteReply(br_num);
			if (res > 0) {
				mav.addObject("msg", "���� ����! ");
				mav.addObject("url",  "content_board.do?board_num="+board_num+"&pageNum="+pageNum);
			} else {
				mav.addObject("msg", "���� ����!");
				mav.addObject("url",  "content_board.do?board_num="+board_num+"&pageNum="+pageNum);
			}

			return mav;
		}
	
	//��� �Է�
	@RequestMapping(value = "/write_reply.do")
	public ModelAndView writeReply(HttpServletRequest req, @ModelAttribute Board_replyDTO dto, BindingResult result) {
		
		int re_group = 0, re_step = 0, re_level = 0;
		
		ModelAndView mav = new ModelAndView("message");
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
			//�ش� �Խñ� ��ȣ�� ��� ��ȣ�� �޾ƿɴϴ�.
			int board_num = dto.getBoard_num();
			int br_num = dto.getBr_num();
			dto.setBoard_num(board_num);
			MemberDTO dto3 = (MemberDTO)session.getAttribute("login_mem");
			dto.setMem_num(dto3.getMem_num());
			dto.setMem_nickname(dto3.getMem_nickname());
			
			//�����϶�
			if(br_num>0) {
				re_group =dto.getBr_re_group();
				re_step=dto.getBr_re_step();
				re_level=dto.getBr_re_level();
				
			}
			mav.addObject("board_num", board_num);
			mav.addObject("br_num", br_num);
			mav.addObject("br_re_group", re_group);
			mav.addObject("br_re_step", re_step);
			mav.addObject("br_re_level", re_level);
			mav.addObject("dto", dto);
			
			int res = boardMapper.insertReply(dto);
			if (res > 0) {
				mav.addObject("msg", "����� ��� �Ǿ����ϴ�.");
				mav.addObject("url", "content_board.do?pageNum=1&board_num="+board_num);
				
			    
				//��� �˶�@@#@#@#@#@#
				MemberDTO dto5 = (MemberDTO)session.getAttribute("login_mem");
				int memNum = alarmMapper.BoardNum(board_num);
				if(dto5.getMem_num()!=memNum) {
				
				String title = alarmMapper.BoardTitle(board_num);
				
				AlarmDTO rm = new AlarmDTO();
				rm.setMem_num(memNum);
				rm.setAlm_cate("���");
				rm.setAlm_content(""+title+""+"�Խñۿ� ���ο� ����� �߰��Ǿ����ϴ�!");
				rm.setAlm_title("��� ���ҽ�");

				alarmMapper.addBoardAlarm(rm);
				}
				
			} else {
				mav.addObject("msg", "��� ��� ����");
				mav.addObject("url", "content_board.do?board_num"+board_num);
			}
			return mav;
		}
//����,�͸� ����
	//�� �κ��� ajax�� �ش� ��� �ؿ� �ٷ� �ۼ� form�� ���� �۾��Դϴ�.
	@ResponseBody
	@RequestMapping("/re_reply.do")
	public ModelAndView re_reply(HttpServletRequest req,int br_num,int pageNum,int board_num) {
		ModelAndView mav = new ModelAndView("/board/Re_replyForm");
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		Board_replyDTO dto = boardMapper.getReply(br_num);
		BoardDTO bdto = boardMapper.getBoard(board_num, "getAnony");
		mav.addObject("dto",dto);
		mav.addObject("bdto",bdto);
		mav.addObject("br_num", br_num);
		mav.addObject("br_re_group",dto.getBr_re_group());
		mav.addObject("br_re_step",dto.getBr_re_step());
		mav.addObject("br_re_level",dto.getBr_re_level());
		mav.addObject("pageNum", pageNum);
		
		return mav;
	}
	//����, �͸� ��� ����
	@RequestMapping(value="/update_reply.do", method = RequestMethod.GET)
	public ModelAndView updateReply(HttpServletRequest req,int br_num,int pageNum) {
		ModelAndView mav = new ModelAndView("/board/updateReplyForm");
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		Board_replyDTO dto = boardMapper.getReply(br_num); // ������ ��� ���� ��������
		mav.addObject("dto",dto);
		mav.addObject("br_num",br_num);
		mav.addObject("pageNum", pageNum);
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="/update_replyOk.do")
	public ModelAndView updateReplyPro(HttpServletRequest req, @RequestParam Map<Object, Object> params) {
		ModelAndView mav = new ModelAndView();
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		String br_num = (String)params.get("br_num"); //��� ��ȣ�� ������ �� 
		Board_replyDTO dto = boardMapper.getReply(Integer.parseInt(br_num));
		//String pageNum = (String)params.get
		dto.setBr_content((String)params.get("br_content")); // ������ ���� set ���ݴϴ�.
		boardMapper.updateReply(dto);
		
		return mav;
	}
	

	//�Խñ� ����
	@RequestMapping("/delete_board.do")
	public ModelAndView deleteBoard(HttpServletRequest req,
			@RequestParam(required = false) Map<String, String> params) {
		ModelAndView mav = new ModelAndView("message");
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message_back");
				mav.addObject("msg","�α��� ���ּ���" );
				return mav;
			}
		int board_num = Integer.parseInt(params.get("board_num"));

		String board_img1 = req.getParameter("board_img1");
		String board_img2 = req.getParameter("board_img2");
		String board_img3 = req.getParameter("board_img3");
		String board_img4 = req.getParameter("board_img4");
	
		
		int res = boardMapper.deleteBoard(board_num);
		if (res > 0) {
			String upPath = (String) session.getAttribute("upPath");

			File file1 = new File(upPath, board_img1); // �̹����� ���� �ϴ� �����ݴϴ�.
			File file2 = new File(upPath, board_img2);
			File file3 = new File(upPath, board_img3);
			File file4 = new File(upPath, board_img4);

			if (file1.exists() || file2.exists() || file3.exists() || file4.exists()) {
				file1.delete();
				file2.delete();
				file3.delete();
				file4.delete();
			}
			//���ϸ���Ʈ �̱�
			 List<BoardFilesDTO> flist = boardMapper.getFiles(board_num);
			 String filename = req.getParameter("filename");
				String upPath1 = (String) session.getAttribute("upPath1");
				File file5 = new File(upPath1, filename);
				 if (file5.exists()) {
			            file5.delete();
			            for (int i = 0; i < flist.size(); i++) {
			               int res2 = boardMapper.fileDelete(flist.get(i).getBoard_num());
			               File file6 = new File(upPath1 + "\\" + flist.get(i).getSavename());
			               if (file6.exists()) {
			                  file6.delete();
			               }
			            }
			}
				 mav.addObject("msg", "�� ���� ����");
		} else {
			mav.addObject("msg", "���� ����");
			mav.addObject("url", "board_free.do?mode="+params.get("mode"));
		}
		mav.addObject("url", "board_free.do?mode="+params.get("mode"));
		return mav;
	}
	
	
	//�Խñ� ������ư Ŭ����
	@RequestMapping(value = "/update_board.do", method = RequestMethod.GET)
	public ModelAndView updateBoard(HttpServletRequest req, int board_num, String mode) {
		BoardDTO dto = boardMapper.getBoard(board_num, "update");
		List<BoardFilesDTO> list = boardMapper.getFiles(board_num);
		ModelAndView mav = new ModelAndView();
		mav.addObject("getBoard", dto);
		mav.addObject("getFiles", list);
		mav.addObject("mode", mode);
		mav.setViewName("board/updateForm");
		return mav;
	}
	//�Խñ� ���� ó��
	@RequestMapping(value = "/update_board.do", method = RequestMethod.POST)
	public ModelAndView updateOkBoard(HttpServletRequest req,@RequestParam("filename") List<MultipartFile> multiFileList, @ModelAttribute BoardDTO dto, BindingResult result)
			throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView("message");
		//�α��� üũ 
		HttpSession session= req.getSession();
			MemberDTO mdto = (MemberDTO)session.getAttribute("login_mem");
			if(mdto == null) {
				session.invalidate();
				mav.setViewName("message");
				mav.addObject("msg","�α��� ���ּ���" );
				mav.addObject("url","login.do" );
				return mav;
			}
		int board_num = Integer.parseInt(req.getParameter("board_num"));
		if (result.hasErrors()) {
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		MultipartFile mf1 = mr.getFile("board_img1");
		MultipartFile mf2 = mr.getFile("board_img2");
		MultipartFile mf3 = mr.getFile("board_img3");
		MultipartFile mf4 = mr.getFile("board_img4");
		MultipartFile mf5 = mr.getFile("filename");
		
		String board_img1 = mf1.getOriginalFilename();
		String board_img2 = mf2.getOriginalFilename();
		String board_img3 = mf3.getOriginalFilename();
		String board_img4 = mf4.getOriginalFilename();
		
		String upPath = session.getServletContext().getRealPath("/resources/img");
		String upPath1 = session.getServletContext().getRealPath("/resources/files");
		
		UUID uuid = UUID.randomUUID();

		if (!mf1.isEmpty()) { // �̹� �̹����� �ְ�, ������ ��
			board_img1 = uuid.toString() + "_" + board_img1;
			File file1 = new File(upPath, board_img1);
			mf1.transferTo(file1);
			
			File file11 = new File(upPath, req.getParameter("board_img1-2"));
			if(file11.exists()) { // �̹� ������ �����Ѵٸ� ����
				file11.delete();
			}
			dto.setBoard_img1(board_img1); // ���ο� �̹��� dto�� �־��ֱ�
			
		} else if (mf1.isEmpty()) { // �̹��� ÷�ΰ� �ȵǾ� �ְ� �����Ҷ� ÷��
			dto.setBoard_img1(req.getParameter("board_img1-2"));
		}

		if (!mf2.isEmpty()) { 
			board_img2 = uuid.toString() + "_" + board_img2;
			
			File file2 = new File(upPath, board_img2);
			mf2.transferTo(file2);
			
			File file22 = new File(upPath, req.getParameter("board_img2-2"));
			if(file22.exists()) {
				file22.delete();
			}
			dto.setBoard_img2(board_img2);
			
		} else if (mf2.isEmpty()) {
			dto.setBoard_img2(req.getParameter("board_img2-2"));
		}

		if (!mf3.isEmpty()) { 
			board_img3 = uuid.toString() + "_" + board_img3;
			
			File file3 = new File(upPath, board_img3);
			mf3.transferTo(file3);
			
			File file33 = new File(upPath, req.getParameter("board_img3-2"));
			if(file33.exists()) {
				file33.delete();
			}
			dto.setBoard_img3(board_img3);
			
		} else if (mf3.isEmpty()) {
			dto.setBoard_img3(req.getParameter("board_img3-2"));
		}
		
		
		if (!mf4.isEmpty()) { 
			board_img4 = uuid.toString() + "_" + board_img4;
			
			File file4 = new File(upPath, board_img4);
			mf4.transferTo(file4);
			
			File file44 = new File(upPath, req.getParameter("board_img4-2"));
			if(file44.exists()) {
				file44.delete();
			}
			dto.setBoard_img4(board_img4);
			
		} else if (mf4.isEmpty()) {
			dto.setBoard_img4(req.getParameter("board_img4-2"));
		}

		BoardFilesDTO dto2 = new BoardFilesDTO();
		String mode = req.getParameter("mode");
		int res = boardMapper.updateBoard(dto);
		if (res > 0) {
			// ���� ���ٿ�ε� ����
			 File fileCheck = new File(upPath1);
	         if (!fileCheck.exists())
	            fileCheck.mkdirs();
	         List<Map<String, String>> fileList = new ArrayList<>();
	         if(multiFileList == null || multiFileList.get(0).getOriginalFilename().equals("")) {
	        	 mav.addObject("msg", "��������! ");
	        		if(mode.equals("anony")) {
	 			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=anony");
	        		}else {
	        		mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=");
	        		}
	 			mav.addObject("getBoard", dto);
	 			return mav;
	         }else if (multiFileList.get(0).getOriginalFilename() != "") {
	            List<BoardFilesDTO> list = boardMapper.getFiles(board_num);
	            for(BoardFilesDTO fdto : list) {
	               int res2 = boardMapper.fileDelete(fdto.getBoard_num());
	               File file2 = new File(upPath1 + "\\" + fdto.getSavename());
	               if(file2.exists()) file2.delete();
	               else {
	            		mav.addObject("msg", "��������!");
	            		 mav.addObject("msg", "��������! ");
	 	        		if(mode.equals("anony")) {
	 	        			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=anony");
	 	        		}else {
	 	        			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=");
	 	        		}
	               }
	            }
	         }
	         //���� �� �� ���ο� ���� �߰���.
	         for (int i = 0; i < multiFileList.size(); i++) {
	            String originFile = multiFileList.get(i).getOriginalFilename();
	            int filesize = (int) multiFileList.get(i).getSize();
	            String ext = originFile.substring(originFile.lastIndexOf("."));
	            String changeFile = UUID.randomUUID().toString() + ext;
	            dto2.setFilename(originFile);
	            dto2.setSavename(changeFile);
	            dto2.setFilesize(filesize);
	            int num = board_num;
	            dto2.setBoard_num(num);
	            int res2 = boardMapper.fileInsert(dto2);
	            Map<String, String> map = new HashMap<>();
	            map.put("filename", originFile);
	            map.put("savename", changeFile);

	            fileList.add(map);
	         }
	         // ���� ���ε�
	         try {
	            for (int i = 0; i < multiFileList.size(); i++) {
	               File uploadFile = new File(upPath1 + "\\" + fileList.get(i).get("savename"));
	               multiFileList.get(i).transferTo(uploadFile);
	            }
	            System.out.println("���� ���� ���ε� ����");
	         } catch (IllegalStateException | IOException e) {
	            System.out.println("�������� ���ε� ���� �Ф�");
	            for (int i = 0; i < multiFileList.size(); i++) {
	               new File(upPath1 + "\\" + fileList.get(i).get("savename")).delete();
	            }
	            e.printStackTrace();
	         }
			mav.addObject("msg", "��������! ");
			 mav.addObject("msg", "��������! ");
     		if(mode.equals("anony")) {
			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=anony");
     		}else {
     		mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode=");
     		}
			mav.addObject("getBoard", dto);
		} else {
			mav.addObject("msg", "��������!");
			mav.addObject("url", "content_board.do?board_num="+board_num+"&pageNum=1&mode="+req.getParameter("mode"));
		}
		return mav;
	}
	
	//�����Խ��� �˻�
	@RequestMapping("board_free_find.do")
	public ModelAndView freeFind(HttpServletRequest req, @RequestParam java.util.Map<String,Object> params) {
		ModelAndView mav = new ModelAndView("board/list_free");
			
		// select ������ �޾ƿ� ���� �������ݴϴ�.
		String select = (String) params.get("select");
			if(select.equals("writer")) {
				select = "m.mem_nickname";
			}else if(select.equals("title")){
				select = "board_title";
			}else {
				select = "board_content";
			}
			//�˻�â�� �Է��� string�� �޾ƿɴϴ�.
			String searchString =(String) params.get("searchString");
			
			params.put("search", select);
			params.put("searchString", searchString); 
			
			//��ȸ�� �� 
			List<BoardDTO> readlist = boardMapper.readlist();
			mav.addObject("readlist", readlist);
			//��ۼ�
			List<BoardDTO> replylist = boardMapper.replylist();
			mav.addObject("replylist", replylist);
			
			//�˻��� ������ �ѹ�
			int pageSize = 10;
			String pageNum = (String) params.get("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int count = boardMapper.getCountFind(params);
			
			params.put("start", startRow);
			params.put("end", endRow);

			if (endRow > count)
				endRow = count;
			List<BoardDTO> list = null;
			if (count > 0) {
				list =  boardMapper.findFree(params);
				int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
				int pageBlock = 2;
				int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
				int endPage = startPage + pageBlock - 1;
				if (endPage > pageCount)
					endPage = pageCount;

				mav.addObject("startPage", startPage);
				mav.addObject("endPage", endPage);
				mav.addObject("pageBlock", pageBlock);
				mav.addObject("pageCount", pageCount);
			}
			
				mav.addObject("count", count);
				mav.addObject("listBoard", list);
		
				return mav;
	}
	
	//�͸�Խ��� �˻�
	@RequestMapping("board_anony_find.do")
	public ModelAndView anonyFind(HttpServletRequest req,@RequestParam String searchString, @RequestParam java.util.Map<String,Object> params) {
		ModelAndView mav = new ModelAndView("board/list_anony");
		String select = (String) params.get("select");
		searchString =(String) params.get("searchString");
		 
		if(select.equals("title")){
			select = "board_title";
		}else {
			select = "board_content";
		}
		params.put("search", select);
		params.put("searchString", searchString);
		
		//��ȸ�� �� 
				List<BoardDTO> readlist = boardMapper.readlist_a();
				mav.addObject("readlist", readlist);
				//��ۼ�
				List<BoardDTO> replylist = boardMapper.replylist_a();
				mav.addObject("replylist", replylist);
				
		//�˻��� ������ �ѹ�
		int pageSize = 10;
		String pageNum = (String) params.get("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCountFind_anony(params);
		
		params.put("start", startRow);
		params.put("end", endRow);

		if (endRow > count)
			endRow = count;
		List<BoardDTO> list = null;
		if (count > 0) {
			list = boardMapper.findAnony(params);
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 2;
			int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
			mav.addObject("count", count);
			mav.addObject("listBoard", list);
		
		return mav;
	}
}